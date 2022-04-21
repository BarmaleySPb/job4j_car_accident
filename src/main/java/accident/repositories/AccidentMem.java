package accident.repositories;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(4);

    private AccidentMem() {
        accidents.put(1, Accident.of("first", "first text", "first address"));
        accidents.put(2, Accident.of("second", "second text", "second address"));
        accidents.put(3, Accident.of("third", "third text", "third address"));
        accidents.put(4, Accident.of("fourth", "fourth text", "fourth address"));

        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Collection<AccidentType> getAllAccidentType() {
        return types.values();
    }

    @Override
    public Collection<Rule> getAllRules() {
        return rules.values();
    }

    @Override
    public void create(Accident accident, String[] ids) {
        Set<Rule> rulesToAdd = new HashSet<>();
        for (String idd : ids) {
            rulesToAdd.add(rules.get(Integer.parseInt(idd)));
        }
        accident.setRules(rulesToAdd);
        accident.setId(id.incrementAndGet());
        accident.setType(types.get(accident.getType().getId()));
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
