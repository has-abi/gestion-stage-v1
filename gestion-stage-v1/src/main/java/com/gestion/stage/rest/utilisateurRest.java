package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.service.UtilisateurService;

@RestController
@RequestMapping("gestion-stage-api/utilisateur")
@CrossOrigin({"http://localhost:4200"})
public class utilisateurRest {
	@Autowired
	private UtilisateurService utilisateurService;
	@GetMapping("/dateNaissance/{dateNaissance}")
	public List<Utilisateur> findByDateNaissanceGreaterThan(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date dateNaissance) {
		return utilisateurService.findByDateNaissanceGreaterThan(dateNaissance);
	}
	@GetMapping("/nom/{nom}")
	public List<Utilisateur> findByNomContains(@PathVariable String nom) {
		return utilisateurService.findByNomContains(nom);
	}
	@GetMapping("/prenom/{prenom}")
	public List<Utilisateur> findByPrenomContains(@PathVariable String prenom) {
		return utilisateurService.findByPrenomContains(prenom);
	}
	@GetMapping("/dateJoin/{dateJoin}")
	public List<Utilisateur> findByDateJoin(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date dateJoin) {
		return utilisateurService.findByDateJoin(dateJoin);
	}
	@PostMapping("/login/")
	public int login(@RequestBody Utilisateur utilisateur) {
		return utilisateurService.login(utilisateur);
	}
	@PostMapping("/register/")
	public int register(@RequestBody Utilisateur utilisateur) {
		return utilisateurService.register(utilisateur);
	}
	@PutMapping("/")
	public int update(@RequestBody Utilisateur utilisateur) {
		return utilisateurService.update(utilisateur);
	}
	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable Long id) {
		return utilisateurService.removeById(id);
	}
	@GetMapping("/")
	public List<Utilisateur> findAll() {
		return utilisateurService.findAll();
	}
}
