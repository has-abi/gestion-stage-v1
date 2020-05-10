package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.service.CoordinateurService;

@RestController
@RequestMapping("gestion-stage-api/coordinateur")
@CrossOrigin({"http://localhost:4200"})
public class CoordinateurRest {
	@Autowired
	private CoordinateurService coordinateurService;
	@GetMapping("/reference/{reference}")
	public Coordinateur findByReference(@PathVariable String reference) {
		return coordinateurService.findByReference(reference);
	}
	@GetMapping("/filiere/id/{id}")
	public Coordinateur findByFiliereId(@PathVariable Long id) {
		return coordinateurService.findByFiliereId(id);
	}
	@PostMapping("/")
	public int save(@RequestBody Coordinateur coordinateur) {
		return coordinateurService.save(coordinateur);
	}
	@DeleteMapping("/reference/{reference}")
	public int removeByReference(@PathVariable String reference) {
		return coordinateurService.removeByReference(reference);
	}
	@PutMapping("/")
	public int update(@RequestBody Coordinateur coordinateur) {
		return coordinateurService.update(coordinateur);
	}
	@GetMapping("/")
	public List<Coordinateur> findAll() {
		return coordinateurService.findAll();
	}
	@GetMapping("/utilisateur/id/{id}")
	public Coordinateur findByUtilisateurId(@PathVariable Long id) {
		return coordinateurService.findByUtilisateurId(id);
	}
}
