package accident.repositories;

import org.springframework.data.repository.CrudRepository;
import accident.models.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}