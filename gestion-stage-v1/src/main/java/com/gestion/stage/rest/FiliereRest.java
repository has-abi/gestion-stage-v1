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

import com.gestion.stage.bean.Filiere;
import com.gestion.stage.service.facade.FiliereService;

@RestController
@RequestMapping("gestion-stage-api/filiere")
@CrossOrigin({"http://localhost:4200"})
public class FiliereRest {
	@Autowired
	private FiliereService filiereService;
	@GetMapping("/departement/{id}")
	public List<Filiere> findByDepartementId(@PathVariable Long id) {
		return filiereService.findByDepartementId(id);
	}
	@GetMapping("/id/{id}")
	public Filiere findById(@PathVariable Long id) {
		return filiereService.findById(id);
	}
	@GetMapping("/")
	public List<Filiere> findAll() {
		return filiereService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody Filiere filiere) {
		return filiereService.save(filiere);
	}
	@PutMapping("/")
	public int update(@RequestBody Filiere filiere) {
		return filiereService.update(filiere);
	}
	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable Long id) {
		return filiereService.removeById(id);
	}
	@GetMapping("/coordinateur/{reference}")
	public Filiere findByCoordinateurReference(@PathVariable String reference) {
		return filiereService.findByCoodinateurReference(reference);
	}
}
