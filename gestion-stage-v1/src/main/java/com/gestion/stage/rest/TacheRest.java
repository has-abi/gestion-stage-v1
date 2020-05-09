package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Tache;
import com.gestion.stage.service.TacheService;

@RestController
@RequestMapping("/stage/tache")
public class TacheRest {
	@Autowired
	private TacheService tacheService;

	@GetMapping("/dateCreation/{dateCreation}")
	public List<Tache> findByDateCreation( @PathVariable Date dateCreation) {
		return tacheService.findByDateCreation(dateCreation);
	}
@GetMapping("/dateLimite/{dateLimite}")
	public List<Tache> findByDateLimite( @PathVariable Date dateLimite) {
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
	public int save( @RequestBody Tache tache) {
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
	

}
