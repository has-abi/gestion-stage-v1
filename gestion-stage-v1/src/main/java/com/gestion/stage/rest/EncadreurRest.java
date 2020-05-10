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

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.service.EncadreurService;

@RestController
@RequestMapping("gestion-stage-api/encadreur")
@CrossOrigin({"http://localhost:4200"})
public class EncadreurRest {
	@Autowired
	private EncadreurService encadreurService;
	@GetMapping("/profession/{profession}")
	public List<Encadreur> findByProfession(@PathVariable String profession) {
		return encadreurService.findByProfession(profession);
	}
	@GetMapping("/type/{type}")
	public List<Encadreur> findByType(String type) {
		return encadreurService.findByType(type);
	}
	@GetMapping("/qualite/{qualite}")
	public List<Encadreur> findByQualite(@PathVariable String qualite) {
		return encadreurService.findByQualite(qualite);
	}
	@GetMapping("/utlisateur/id/{id}")
	public Encadreur findByUtilisateurId(@PathVariable Long id) {
		return encadreurService.findByUtilisateurId(id);
	}
	@GetMapping("/id/{id}")
	public Encadreur findById(@PathVariable Long id) {
		return encadreurService.findById(id);
	}	
	@GetMapping("/")
	public List<Encadreur> findAll() {
		return encadreurService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody Encadreur encadreur) {
		return encadreurService.save(encadreur);
	}
	@PutMapping("/")
	public int update(@RequestBody Encadreur encadreur) {
		return encadreurService.update(encadreur);
	}
	@DeleteMapping("/reference/{reference}")
	public int removeById(String reference) {
		return encadreurService.removeByReference(reference);
	}
	@GetMapping("/reference/{reference}")
	public Encadreur findByReference(String reference) {
		return encadreurService.findByReference(reference);
	}
}
