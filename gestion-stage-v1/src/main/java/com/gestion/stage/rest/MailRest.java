package com.gestion.stage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.service.impl.MailService;
import com.gestion.stage.utils.Email;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/mail/")
@CrossOrigin({ "http://localhost:4200" })
public class MailRest {

	@Autowired
	private MailService notificationService;

	@PostMapping("/send")
	public void sendEmailOfPwdRenew(@RequestBody String username) {
		notificationService.sendEmailOfPwdRenew(username);
		try {
			notificationService.sendEmail(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
	}

	private Email email = new Email();

	@GetMapping("username/{username}/cne/{cne}")
	public String send(@PathVariable String username, @PathVariable String cne) {
		email.setEmailAddress(username);
		email.setCne(cne);
		try {
			notificationService.sendEmail(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "E-mail est envoyé!";
	}
}
