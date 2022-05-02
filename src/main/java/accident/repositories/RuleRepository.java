package accident.repositories;

import org.springframework.data.repository.CrudRepository;
import accident.models.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}