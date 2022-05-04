package accident.repositories;

import org.springframework.data.repository.CrudRepository;
import accident.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}