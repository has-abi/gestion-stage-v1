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

import com.gestion.stage.bean.Administrateur;
import com.gestion.stage.service.facade.AdministrateurService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/administrateur")
@CrossOrigin({ "http://localhost:4200" })
public class AdministrateurRest {
	@Autowired
	private AdministrateurService administrateurService;

	@GetMapping("/")
	public List<Administrateur> findAll() {
		return administrateurService.findAll();
	}

	@PostMapping("/")
	public int save(@RequestBody Administrateur administrateur) {
		return administrateurService.save(administrateur);
	}

	@PutMapping("/")
	public int update(@RequestBody Administrateur administrateur) {
		return administrateurService.update(administrateur);
	}

	@DeleteMapping("/ref/{ref}")
	public int removeByRef(String ref) {
		return administrateurService.removeByRef(ref);
	}

	@GetMapping("/user/id/{id}")
	public Administrateur findByUserId(@PathVariable Long id) {
		return administrateurService.findByUserId(id);
	}

	@GetMapping("/ref/{ref}")
	public Administrateur findByRef(@PathVariable String ref) {
		return administrateurService.findByRef(ref);
	}
}
