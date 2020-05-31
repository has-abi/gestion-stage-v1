package com.gestion.stage;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.gestion.stage.service.facade.FileStorageService;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class) 


public class GestionStageV1Application{
//	@Resource
//	FileStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(GestionStageV1Application.class, args);
	}

}
