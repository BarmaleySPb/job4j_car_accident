package accident.services;

import accident.models.Accident;
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
}
