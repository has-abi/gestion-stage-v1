package com.gestion.stage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.UserDao;
import com.gestion.stage.service.facade.EtudiantService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.CodeSession;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.Email;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class MailService {
	private JavaMailSender javaMailSender;

	@Autowired
	private EtudiantService etudiantService;

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(Email email) throws MailException {
		Etudiant foundedEtud = etudiantService.findByCin(email.getCne());

		String code = generateCode();
		if (foundedEtud != null) {
			User foundedU = foundedEtud.getUser();
			foundedU.setCodeConfirm(code);
			userDao.save(foundedU);
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(email.getEmailAddress());
			mail.setSubject("Compte Confirmation");
			mail.setText("Le code de confirmation de votre compte sur le platform Gestion Des Stages de la Faculté"
					+ " Des Sciences Et Techniques Marrakech: " + code);
			javaMailSender.send(mail);
		}

	}

	String generateCode() {
		String[] t = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String code = "";
		for (int i = 0; i < 10; i++) {
			int rand = (int) (Math.random() * 10);
			if (i < 6) {
				code += t[rand];
			}
		}
		return code;
	}

	public void sendEmailOfPwdRenew(String username) {
		User foundedUser = userService.findByUsername(username);
		if (foundedUser != null) {
			String code = generateCode();
			CodeSession.addSessionCode(username, code, DateUtil.getDate().getTime() + 5 * 60 * 1000);
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(username);
			mail.setSubject("Réinitialisation de mot de passe");
			mail.setText(
					"Le code pour la réinitialisation de votre mot de passe  sur le platform gestion des stages de la Faculté"
							+ " Des Sciences Et Techniques Marrakech: " + code);
			javaMailSender.send(mail);
		}
	}
}
