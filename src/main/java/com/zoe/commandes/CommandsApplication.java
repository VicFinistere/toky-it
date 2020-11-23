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
public class CommandsApplication {

	private static final Logger log = LoggerFactory.getLogger(CommandsApplication.class);

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CommandRepository repository) {
		return (args) -> {

			Date today = new Date();

			// save a few Commands
			repository.save(new Command(today));
			repository.save(new Command(today));
			repository.save(new Command(today, true));
			repository.save(new Command(today, false));
			repository.save(new Command(today));

			// fetch all Commands
			log.info("Commands found with findAll():");
			log.info("-------------------------------");
			for (Command Command : repository.findAll()) {
				log.info(Command.toString());
			}
			log.info("");

			// fetch an individual Command by ID
			Command Command = repository.findById(1L);
			log.info("Command found with findById(1L):");
			log.info("--------------------------------");
			log.info(Command.toString());
			log.info("");

			// fetch Commands by last name
			log.info("Command found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByDate(today).forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Command bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}