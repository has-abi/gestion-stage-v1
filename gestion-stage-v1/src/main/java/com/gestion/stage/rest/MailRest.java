package com.gestion.stage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.service.impl.MailService;
import com.gestion.stage.utils.Email;

@RestController
@RequestMapping("gestion-stage-api/mail/")
public class MailRest {
	
	@Autowired
	private MailService notificationService;
	
	private Email email = new Email();
	
	@GetMapping("username/{username}/cne/{cne}")
	public String send(@PathVariable String username,@PathVariable String cne) {
		email.setEmailAddress(username); 
		email.setCne(cne);
		try {
			notificationService.sendEmail(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}
