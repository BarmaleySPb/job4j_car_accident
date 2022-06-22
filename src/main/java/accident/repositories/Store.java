package accident.repositories;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;

import java.util.Collection;

public interface Store {
    Collection<Accident> getAll();
    Collection<AccidentType> getAllAccidentType();
    Collection<Rule> getAllRules();
    Accident create(Accident accident, String[] ids);
    void update(Accident accident);
    Accident get(int id);
}
