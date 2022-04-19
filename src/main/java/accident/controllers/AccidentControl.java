package accident.controllers;

import accident.services.AccidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.models.Accident;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.create(accident);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String update(@RequestParam("id") int id, @RequestParam("name") String name,
                         @RequestParam("text") String text, @RequestParam("address") String address) {
        Accident accident = service.get(id);
        accident.setName(name);
        accident.setText(text);
        accident.setAddress(address);
        service.update(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = service.get(id);
        model.addAttribute("accident", accident);
        return "accident/edit";
    }
}