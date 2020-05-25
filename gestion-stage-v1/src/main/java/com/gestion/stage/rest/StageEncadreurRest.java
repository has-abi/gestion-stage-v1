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

import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.service.facade.StageEncadrantService;

@RestController
@RequestMapping("gestion-stage-api/stage-encadreur")
@CrossOrigin({"http://localhost:4200"})
public class StageEncadreurRest {
	@Autowired
	private StageEncadrantService stageEncadrantService;
	@GetMapping("/encadreur/nom/{nom}/prenom/{prenom}")
	public List<StageEncadreur> findByEncadreurUserNomContainsOrEncadreurUserPrenomContains(@PathVariable String nom,
			@PathVariable String prenom) {
		return stageEncadrantService.findByEncadreurUserNomContainsOrEncadreurUserPrenomContains(nom, prenom);
	}
	@GetMapping("/stage/reference/{reference}")
	public List<StageEncadreur> findByStageReference(@PathVariable String reference) {
		return stageEncadrantService.findByStageReference(reference);
	}
	@GetMapping("/encadreur/reference/{reference}")
	public List<StageEncadreur> findByEncadreurReference(@PathVariable String reference) {
		return stageEncadrantService.findByEncadreurReference(reference);
	}
	@GetMapping("/stage/reference/{stage}/encadreur/reference/{reference}")
	public StageEncadreur findByStageReferenceAndEncadreurReference(@PathVariable String stage,@PathVariable String reference) {
		return stageEncadrantService.findByStageReferenceAndEncadreurReference(stage, reference);
	}
	@PostMapping("/")
	public int save(@RequestBody StageEncadreur stageEncadreur) {
		return stageEncadrantService.save(stageEncadreur);
	}
	@GetMapping("/")
	public List<StageEncadreur> findAll() {
		return stageEncadrantService.findAll();
	}
	@PutMapping("/")
	public int update(@RequestBody StageEncadreur stageEncadreur) {
		return stageEncadrantService.update(stageEncadreur);
	}
	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable Long id) {
		return stageEncadrantService.removeById(id);
	}
}
