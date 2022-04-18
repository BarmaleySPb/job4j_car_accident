package accident.controllers;

import accident.services.AccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {
    private final AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.findAll());
        return "index";
    }
}
