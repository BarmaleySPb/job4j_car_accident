package accident.services;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;
import accident.repositories.Store;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService {
    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public Collection<Accident> getAll() {
        return store.getAll();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return store.getAllAccidentType();
    }

    public Collection<Rule> getAllRules() {
        return store.getAllRules();
    }

    public void create(Accident accident, String[] ids) {
        store.create(accident, ids);
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public Accident get(int id) {
        return store.get(id);
    }
}
