package eu.xylandia.toky_it.controller;

import eu.xylandia.toky_it.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

    @RequestMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @GetMapping("/answer")
    public String getAnswer(Model model) {
        return "answer";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

}

