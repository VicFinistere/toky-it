package eu.xylandia.toky_it.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainRestController {

    @PostMapping("/getInput")
    public String getInput(@RequestParam Optional<String> user_input) {
        return "User input is : " + user_input;
    }
}
