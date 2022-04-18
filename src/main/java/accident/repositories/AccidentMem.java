package accident.repositories;

import accident.models.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private AccidentMem() {
        accidents.put(1, Accident.of("first", "first text", "first address"));
        accidents.put(2, Accident.of("second", "second text", "second address"));
        accidents.put(3, Accident.of("third", "third text", "third address"));
        accidents.put(4, Accident.of("fourth", "fourth text", "fourth address"));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}