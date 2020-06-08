package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
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
import com.gestion.stage.service.facade.OrganismeAccueilService;
import com.gestion.stage.utils.OrganismeStatistics;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("gestion-stage-api/organismeAccueil")
@CrossOrigin({"http://localhost:4200"})
public class OrganismeRest {
	@Autowired
	private OrganismeAccueilService organismeAccueilService;
	@GetMapping("/count")
	public int countOrganismes() {
		return organismeAccueilService.countOrganismes();
	}
	@GetMapping("/statistics/filiere/id/{id}")
	public List<OrganismeStatistics> organismesParFiliere(@PathVariable Long id) {
		return organismeAccueilService.organismesParFiliere(id);
	}
	@GetMapping("/page/{page}/size/{size}")
	public Page<OrganismeAccueil> findAllWithPagination(@PathVariable int page,@PathVariable int size) {
		return organismeAccueilService.findAllWithPagination(page, size);
	}
	@GetMapping("/search")
	public ResponseEntity<List<OrganismeAccueil>> searchForOrganismes(@SearchSpec Specification<OrganismeAccueil> spec) {
		return organismeAccueilService.searchForOrganismes(spec);
	}
	@GetMapping("/type/{type}/page/{page}/size/{size}")
	public Page<OrganismeAccueil> findByTypeOrganismeType(@PathVariable String type,@PathVariable int page,@PathVariable int size) {
		return organismeAccueilService.findByTypeOrganismeType(type,page,size);
	}
	@GetMapping("/typeService/{type}/page/{page}/size/{size}")
	public Page<OrganismeAccueil> findByTypeServiceOrganismeType(@PathVariable String type,@PathVariable int page,@PathVariable int size) {
		return organismeAccueilService.findByTypeServiceOrganismeType(type,page,size);
	}
	@GetMapping("/ville/{nom}/page/{page}/size/{size}")
	public Page<OrganismeAccueil> findByVilleNom(@PathVariable String nom,@PathVariable int page,@PathVariable int size) {
		return organismeAccueilService.findByVilleNom(nom,page,size);
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
