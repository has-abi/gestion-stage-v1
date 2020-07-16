package com.gestion.stage.rest;

import java.util.Date;
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

import com.gestion.stage.bean.Tache;
import com.gestion.stage.service.facade.TacheService;
import com.sipios.springsearch.anotation.SearchSpec;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/tache")
@CrossOrigin({ "http://localhost:4200" })
public class TacheRest {
	@Autowired
	private TacheService tacheService;

	@GetMapping("/search")
	public ResponseEntity<List<Tache>> searchForTaches(@SearchSpec Specification<Tache> spec) {
		return tacheService.searchForTaches(spec);
	}

	@GetMapping("/encadreur/reference/{reference}/page/{page}/size/{size}")
	public Page<Tache> findByEncadreurReference(@PathVariable String reference, @PathVariable int page,
			@PathVariable int size) {
		return tacheService.findByEncadreurReference(reference, page, size);
	}

	@GetMapping("/etudiant/id/{id}/page/{page}/size/{size}")
	public Page<Tache> findByEtudiant(@PathVariable Long id, @PathVariable int page, @PathVariable int size) {
		return tacheService.findByEtudiant(id, page, size);
	}

	@GetMapping("/stage/reference/{reference}")
	public List<Tache> findByStageReference(@PathVariable String reference) {
		return tacheService.findByStageReference(reference);
	}

	@GetMapping("/dateCreation/{dateCreation}")
	public List<Tache> findByDateCreation(@PathVariable Date dateCreation) {
		return tacheService.findByDateCreation(dateCreation);
	}

	@GetMapping("/dateLimite/{dateLimite}")
	public List<Tache> findByDateLimite(@PathVariable Date dateLimite) {
		return tacheService.findByDateLimite(dateLimite);
	}

	@GetMapping("/reference/{reference}")
	public Tache findByReference(@PathVariable String reference) {
		return tacheService.findByReference(reference);
	}

	@DeleteMapping("/delete/reference/{reference}")
	public int deleteByReference(@PathVariable String reference) {
		return tacheService.deleteByReference(reference);
	}

	@PostMapping("/")
	public int save(@RequestBody Tache tache) {
		return tacheService.save(tache);
	}

	@PutMapping()
	public int updateTache(@RequestBody Tache tache) {
		return tacheService.updateTache(tache);
	}

	@GetMapping("/")
	public List<Tache> findAll() {
		return tacheService.findAll();
	}

	@PutMapping("/valider/reference/{reference}")
	public int validerTache(@PathVariable String reference) {
		return tacheService.validerTache(reference);
	}

	@PutMapping("/effectuer/reference/{reference}")
	public int effectuerTache(@PathVariable String reference) {
		return tacheService.effectuerTache(reference);
	}

}
