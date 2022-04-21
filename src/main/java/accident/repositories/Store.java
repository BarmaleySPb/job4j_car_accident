package accident.repositories;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;

import java.util.Collection;

public interface Store {

    Collection<Accident> findAll();
    Collection<AccidentType> getAllAccidentType();
    Collection<Rule> getAllRules();
    void create(Accident accident, String[] ids);
    void update(Accident accident);
    Accident get(int id);
}
