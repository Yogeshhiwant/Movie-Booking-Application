package com.infy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.infy.repository")
public class MovieBookingSpringProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MovieBookingSpringProjectApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Movie Booking Application !");
    }
}

		
		
		
				
		
	
	 