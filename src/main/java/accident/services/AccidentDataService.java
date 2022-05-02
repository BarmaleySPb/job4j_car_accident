package accident.services;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;
import accident.repositories.AccidentRepository;
import accident.repositories.AccidentTypeRepository;
import accident.repositories.RuleRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AccidentDataService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentDataService(AccidentRepository accidentRepository,
                           AccidentTypeRepository accidentTypeRepository,
                           RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<AccidentType> getAllAccidentType() {
        return (Collection<AccidentType>) accidentTypeRepository.findAll();
    }

    public Collection<Rule> getAllRules() {
        return (Collection<Rule>) ruleRepository.findAll();
    }

    public void create(Accident accident, String[] ids) {
        accident.setRules(getRules(ids));
        accidentRepository.save(accident);
    }

    public void update(Accident accident) {
        accidentRepository.update(accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
    }

    public Accident get(int id) throws NotFoundException {
        Optional<Accident> acc = accidentRepository.findById(id);
        if (acc.isPresent()) {
            return acc.get();
        } else {
            throw new NotFoundException("Accident not found");
        }
    }

    private Set<Rule> getRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String rId : ids) {
            Optional<Rule> rule = ruleRepository.findById(Integer.valueOf(rId));
            rule.ifPresent(rules::add);
        }
        return rules;
    }
}
