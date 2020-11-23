package com.zoe.commandes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Controller
@SpringBootApplication
public class CommandesApplication {

	private static final Logger log = LoggerFactory.getLogger(CommandesApplication.class);

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CommandeRepository repository) {
		return (args) -> {};
	}
}