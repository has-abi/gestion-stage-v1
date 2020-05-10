package com.gestion.stage.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.service.OrganismeAccueilService;

@RestController
@RequestMapping("gestion-stage-api/organismeAccueil")
@CrossOrigin({"http://localhost:4200"})
public class OrganismeRest {
	private OrganismeAccueilService organismeAccueilService;
	@GetMapping("/type/{type}")
	public List<OrganismeAccueil> findByTypeOrganismeType(@PathVariable String type) {
		return organismeAccueilService.findByTypeOrganismeType(type);
	}
	@GetMapping("/typeService/{type}")
	public List<OrganismeAccueil> findByTypeServiceOrganismeType(@PathVariable String type) {
		return organismeAccueilService.findByTypeServiceOrganismeType(type);
	}
	@GetMapping("/ville/{nom}")
	public List<OrganismeAccueil> findByVilleNom(@PathVariable String nom) {
		return organismeAccueilService.findByVilleNom(nom);
	}
	@GetMapping("/raisonSociale/{raisonSocial}")
	public OrganismeAccueil findByRaisonSocial(@PathVariable String raisonSocial) {
		return organismeAccueilService.findByRaisonSocial(raisonSocial);
	}
	@GetMapping("/")
	public List<OrganismeAccueil> findAll() {
		return organismeAccueilService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody OrganismeAccueil organismeAccueil) {
		return organismeAccueilService.save(organismeAccueil);
	}
	@PutMapping("/")
	public int update(@RequestBody OrganismeAccueil organismeAccueil) {
		return organismeAccueilService.update(organismeAccueil);
	}
	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable long id) {
		return organismeAccueilService.removeById(id);
	}
}
