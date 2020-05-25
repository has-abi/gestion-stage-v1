package com.gestion.stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) 
public class GestionStageV1Application {

	public static void main(String[] args) {
		SpringApplication.run(GestionStageV1Application.class, args);
	}

}
