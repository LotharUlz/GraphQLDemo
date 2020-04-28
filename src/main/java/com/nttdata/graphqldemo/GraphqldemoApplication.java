package com.nttdata.graphqldemo;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nttdata.graphqldemo.model.partner.User;
import com.nttdata.graphqldemo.controller.InsuranceService;

@SpringBootApplication
public class GraphqldemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(GraphqldemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(InsuranceService foodService) {
	    return args -> {
	        Stream.of("Meier", "Huber", "Schmidt", "Klein", "Leutheusser-Schnarrenberger")
	        .forEach(name -> {
	            User user = new User();
	            user.setName(name);
	            user.setCountryCodeId("DE");
	            foodService.saveUser(user);
	        });
	        foodService.getUsers().forEach(System.out::println);
	    };
	}
}
