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

import com.gestion.stage.bean.TypeStage;
import com.gestion.stage.service.TypeStageService;

@RestController
@RequestMapping("gestion-stage-api/typeStage")
@CrossOrigin({"http://localhost:4200"})
public class TypeStageRest {
	@Autowired
	private TypeStageService typeStageService;
	@GetMapping("/libelle/{libelle}")
	public TypeStage findByLibelle(@PathVariable String libelle) {
		return typeStageService.findByLibelle(libelle);
	}
	@GetMapping("/")
	public List<TypeStage> findAll() {
		return typeStageService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody TypeStage typeStage) {
		return typeStageService.save(typeStage);
	}
	@PutMapping("/")
	public int update(@RequestBody TypeStage typeStage) {
		return typeStageService.update(typeStage);
	}
	@DeleteMapping("/libelle/{libelle}")
	public int removeBylibelle(@PathVariable String libelle) {
		return typeStageService.removeBylibelle(libelle);
	}
}
