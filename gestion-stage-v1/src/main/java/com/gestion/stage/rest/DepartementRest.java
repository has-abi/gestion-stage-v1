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

import com.gestion.stage.bean.Departement;
import com.gestion.stage.service.facade.DepartementService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/departement")
@CrossOrigin({ "http://localhost:4200" })
public class DepartementRest {
	@Autowired
	private DepartementService departementService;

	@PostMapping("/")
	public int save(@RequestBody Departement departement) {
		return departementService.save(departement);
	}

	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable Long id) {
		return departementService.removeById(id);
	}

	@PutMapping("/")
	public int update(@RequestBody Departement departement) {
		return departementService.update(departement);
	}

	@GetMapping("/")
	public List<Departement> findAll() {
		return departementService.findAll();
	}
}
