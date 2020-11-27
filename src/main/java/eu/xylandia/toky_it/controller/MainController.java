package eu.xylandia.toky_it.controller;

import eu.xylandia.toky_it.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("bearer", "${bearer}");
        return "index";
    }

    @GetMapping("/answer")
    public String getAnswer(Model model) {
        return "answer";
    }

}

