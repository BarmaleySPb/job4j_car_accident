package accident.repositories;

import accident.models.Accident;
import accident.models.AccidentType;

import java.util.Collection;

public interface Store {

    Collection<Accident> findAll();
    Collection<AccidentType> getAllAccidentType();
    void create(Accident accident);
    void update(Accident accident);
    Accident get(int id);
}
