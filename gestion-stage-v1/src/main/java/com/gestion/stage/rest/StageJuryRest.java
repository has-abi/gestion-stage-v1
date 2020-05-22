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

import com.gestion.stage.bean.StageMembreJury;
import com.gestion.stage.service.StageMembreJuryService;

@RestController
@RequestMapping("gestion-stage-api/stage-jury")
@CrossOrigin({"http://localhost:4200"})
public class StageJuryRest {
	@Autowired
	private StageMembreJuryService stageMembreJuryService;
	@GetMapping("/jury/nom/{nom}/prenom/{prenom}")
	public List<StageMembreJury> findByMembreJuryUtilisateurNomContainsOrMembreJuryUtilisateurPrenomContains(String nom,
			String prenom) {
		return stageMembreJuryService.findByMembreJuryUtilisateurNomContainsOrMembreJuryUtilisateurPrenomContains(nom,
				prenom);
	}
	@GetMapping("/stage/reference/{reference}")
	public List<StageMembreJury> findByStageReference(@PathVariable String reference) {
		return stageMembreJuryService.findByStageReference(reference);
	}
	@GetMapping("/jury/reference/{reference}")
	public List<StageMembreJury> findBMembreJuryReference(@PathVariable String reference) {
		return stageMembreJuryService.findBMembreJuryReference(reference);
	}
	@GetMapping("/stage/reference/{stage}/jury/reference/{reference}")
	public StageMembreJury findByMembreJuryReferenceAndStageReference(@PathVariable String reference,@PathVariable String stage) {
		return stageMembreJuryService.findByMembreJuryReferenceAndStageReference(reference, stage);
	}
	 @GetMapping("/")
	public List<StageMembreJury> findAll() {
		return stageMembreJuryService.findAll();
	}
	 @PostMapping("/")
	public int save(@RequestBody StageMembreJury stageMembreJury) {
		return stageMembreJuryService.save(stageMembreJury);
	}
	 @PutMapping("/")
	public int update(@RequestBody StageMembreJury stageMembreJury) {
		return stageMembreJuryService.update(stageMembreJury);
	}
	 @DeleteMapping("/id/{id}")
	public int removeByid(@PathVariable Long id) {
		return stageMembreJuryService.removeByid(id);
	}
	
}
