package accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accident.models.Accident;
import accident.repositories.AccidentRepository;

import java.util.*;

@Controller
public class IndexControl {
    private final AccidentRepository accidents;

    public IndexControl(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> res = new ArrayList<>();
        accidents.findAll().forEach(res::add);
        res.sort(Comparator.comparing(Accident::getId));
        model.addAttribute("accidents", res);
        return "index";
    }
}