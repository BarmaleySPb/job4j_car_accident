package accident.services;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.repositories.Store;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService {
    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return store.getAllAccidentType();
    }

    public void create(Accident accident) {
        store.create(accident);
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public Accident get(int id) {
        return store.get(id);
    }
}
