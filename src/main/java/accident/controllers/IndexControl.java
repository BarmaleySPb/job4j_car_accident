package accident.controllers;

import accident.repositories.AccidentHibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {
    private final AccidentHibernate accidents;

    public IndexControl(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.getAll());
        return "index";
    }
}