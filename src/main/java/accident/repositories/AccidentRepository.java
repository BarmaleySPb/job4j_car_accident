package accident.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import accident.models.Accident;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Modifying
    @Query(value = "update Accident a set a.name = ?1, a.text = ?2, a.address = ?3 where a.id = ?4")
    void update(String name, String text, String address, Integer id);
}