package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.infy.repository")
public class MovieBookingSpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieBookingSpringProjectApplication.class, args);

    }


}

		
		
		
				
		
	
	 