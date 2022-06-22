package accident.repositories;

import org.springframework.data.repository.CrudRepository;
import accident.models.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}