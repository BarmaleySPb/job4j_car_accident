package accident.controllers;

import accident.services.AccidentDataService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.models.Accident;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentDataService service;

    public AccidentControl(AccidentDataService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAllAccidentType());
        model.addAttribute("rules", service.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.create(accident, ids);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute Accident accident) throws NotFoundException {
        accident.setType(service.get(accident.getId()).getType());
        accident.setRules(service.get(accident.getId()).getRules());
        service.update(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) throws NotFoundException {
        model.addAttribute("accident", service.get(id));
        return "accident/edit";
    }
}