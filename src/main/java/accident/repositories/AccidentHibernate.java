package accident.repositories;

import accident.models.AccidentType;
import accident.models.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import accident.models.Accident;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentHibernate implements Store {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident create(Accident accident, String[] ids) {
        try (Session session = sf.openSession()) {
            var txn = session.beginTransaction();
            accident.setType(getTypeById(accident.getType().getId()));
            accident.setRules(getRules(ids));
            session.save(accident);
            txn.commit();
        }
        return accident;
    }

    @Override
    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            var transaction = session.beginTransaction();
            session.update(accident);
            transaction.commit();
        }
    }

    @Override
    public Accident get(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from " +
                            "Accident a " +
                            "join fetch a.rules r " +
                            "join fetch a.type", Accident.class)
                    .list();
        }
    }

    @Override
    public Collection<AccidentType> getAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public AccidentType getTypeById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }

    @Override
    public Collection<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    private Set<Rule> getRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        try (Session session = sf.openSession()) {
            for (String rId : ids) {
                Rule rule = session.find(Rule.class, Integer.parseInt(rId));
                rules.add(rule);
            }
        }
        return rules;
    }
}