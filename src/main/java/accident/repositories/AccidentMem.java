package accident.repositories;

import accident.models.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger ID = new AtomicInteger(4);

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

    @Override
    public void create(Accident accident) {
        accident.setId(ID.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    @Override
    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    @Override
    public Accident get(int id) {
        return accidents.get(id);
    }
}
