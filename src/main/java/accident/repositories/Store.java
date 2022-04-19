package accident.repositories;

import accident.models.Accident;

import java.util.Collection;

public interface Store {

    Collection<Accident> findAll();
    void create(Accident accident);
    void update(Accident accident);
    Accident get(int id);
}
