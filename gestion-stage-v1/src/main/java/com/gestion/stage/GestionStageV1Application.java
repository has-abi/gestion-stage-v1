package com.gestion.stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

<<<<<<< HEAD
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
=======
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) 
>>>>>>> branch 'master' of https://github.com/has-abi/gestion-stage-v1.git
public class GestionStageV1Application {

	public static void main(String[] args) {
		SpringApplication.run(GestionStageV1Application.class, args);
	}

}
