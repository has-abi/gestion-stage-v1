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

import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.service.facade.EncadreurService;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("gestion-stage-api/encadreur")
@CrossOrigin({"http://localhost:4200"})
public class EncadreurRest {
	@Autowired
	private EncadreurService encadreurService;
	@GetMapping("/count")
	public int countEncadreurs() {
		return encadreurService.countEncadreurs();
	}
	@GetMapping("/filiere/id/{id}")
	public List<Encadreur> findByFiliere(@PathVariable Long id) {
		return encadreurService.findByFiliere(id);
	}
	@GetMapping("/user/email/{email}")
	public Encadreur findByUserEmail(@PathVariable String email) {
		return encadreurService.findByUserEmail(email);
	}
	@GetMapping("/coordinateur/id/{id}/page/{page}/size/{size}/sort/{sort}")
	public Page<Encadreur> findByCoordinateur(@PathVariable Long id,@PathVariable int page,@PathVariable int size,String sort) {
		return encadreurService.findByCoordinateur(id, page, size,sort);
	}
	@GetMapping("/user/nom/{nom}/prenom/{prenom}/page/{page}/size/{size}")
	public Page<Encadreur> findByUserNomContainsOrUserPrenomContains(@PathVariable String nom,@PathVariable String prenom,
			@PathVariable int page,@PathVariable int size) {
		return encadreurService.findByUserNomContainsOrUserPrenomContains(nom, prenom, page, size);
	}
	@GetMapping("/search")
	public ResponseEntity<List<Encadreur>> searchForEncadreurs(@SearchSpec Specification<Encadreur> spec) {
		return encadreurService.searchForEncadreurs(spec);
	}
	@GetMapping("/page/{page}/size/{size}")
	public Page<Encadreur> findAllWithPaginition(@PathVariable int page,@PathVariable int size) {
		return encadreurService.findAllWithPaginition(page, size);
	}
	@GetMapping("/profession/{profession}/page/{page}/size/{size}")
	public Page<Encadreur> findByProfession(@PathVariable String profession,@PathVariable int page,@PathVariable int size) {
		return encadreurService.findByProfession(profession,page,size);
	}
	@GetMapping("/type/{type}/page/{page}/size/{size}")
	public Page<Encadreur> findByType(String type,@PathVariable int page,@PathVariable int size) {
		return encadreurService.findByType(type,page,size);
	}
	@GetMapping("/qualite/{qualite}/page/{page}/size/{size}")
	public Page<Encadreur> findByQualite(@PathVariable String qualite,@PathVariable int page,@PathVariable int size) {
		return encadreurService.findByQualite(qualite,page,size);
	}
	@GetMapping("/user/id/{id}")
	public Encadreur findByUserId(@PathVariable Long id) {
		return encadreurService.findByUserId(id);
	}
	@GetMapping("/id/{id}")
	public Encadreur findById(@PathVariable Long id) {
		return encadreurService.findById(id);
	}	
	@GetMapping("/page/{page}/size/{size}/sort/{sort}")
	public Page<Encadreur> findAll(@PathVariable int page,@PathVariable int size,@PathVariable String sort) {
		return encadreurService.findAll(page,size,sort);
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
	public int removeByReference(String reference) {
		return encadreurService.removeByReference(reference);
	}
	@GetMapping("/reference/{reference}")
	public Encadreur findByReference(String reference) {
		return encadreurService.findByReference(reference);
	}
}
