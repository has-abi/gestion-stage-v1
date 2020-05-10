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
import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.service.AdministrateurService;

@RestController
@RequestMapping("gestion-stage-api/stage-encadreur")
@CrossOrigin({"http://localhost:4200"})
public class AdministrateurRest {
	@Autowired
	private AdministrateurService administrateurService;
	@GetMapping("/profession/{profession}")
	public List<Administrateur> findByProfessionContains(@PathVariable String profession) {
		return administrateurService.findByProfessionContains(profession);
	}
	@GetMapping("/etablissment/{etablissement}")
	public List<Administrateur> findByEtablissement(@PathVariable Etablissement etablissement) {
		return administrateurService.findByEtablissement(etablissement);
	}
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
	@GetMapping("/utilisateur/id/{id}")
	public Administrateur findByUtilisateurId(@PathVariable Long id) {
		return administrateurService.findByUtilisateurId(id);
	}
	@GetMapping("/ref/{ref}")
	public Administrateur findByRef(@PathVariable String ref) {
		return administrateurService.findByRef(ref);
	}
}
