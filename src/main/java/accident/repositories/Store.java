package accident.repositories;

import accident.models.Accident;

import java.util.Collection;

public interface Store {

    Collection<Accident> findAll();
}
