package com.hitarth.my_expense_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "com.hitarth.my_expense_tracker")
public class EtUserAuthServiceApplication {

	public static void main(String[] args) {
		System.out.println("Started");
		log.info("Yess");
		//SpringApplication.run(EtUserAuthServiceApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(EtUserAuthServiceApplication.class, args);

        // DEBUG: Print all loaded bean names
        // System.out.println("==== LOADED BEANS ====");
        // for (String name : context.getBeanDefinitionNames()) {
        //     System.out.println(name);
        // }
	}

}
