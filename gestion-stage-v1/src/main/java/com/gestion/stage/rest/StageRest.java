package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.TypeStage;
import com.gestion.stage.service.StageService;

@RestController
@RequestMapping("gestion-stage-api/stage")
@CrossOrigin({"http://localhost:4200"})
public class StageRest {
	
	@Autowired
	private StageService stageService;
	@GetMapping("/dateDebut/{dateDebut}")
	public List<Stage> findByDateDebut(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date dateDebut) {
		return stageService.findByDateDebut(dateDebut);
	}
	@GetMapping("/dateFin/{dateFin}")
	public List<Stage> findByDateFin(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date dateFin) {
		return stageService.findByDateFin(dateFin);
	}
	@GetMapping("/typeStage/{typeStage}")
	public List<Stage> findByTypeStage(TypeStage typeStage) {
		return stageService.findByTypeStage(typeStage);
	}
	@GetMapping("/dateFin1/{date1}/dateFin2/{date2}")
	public List<Stage> findByDateFinBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date date1,@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date date2) {
		return stageService.findByDateFinBetween(date1, date2);
	}
	@GetMapping("/sujet/{sujet}")
	public List<Stage> findBySujetContains(@PathVariable String sujet) {
		return stageService.findBySujetContains(sujet);
	}
	@GetMapping("/organismeAccueil/{raisonSocial}")
	public List<Stage> findByOrganismeAccueilRaisonSocial(@PathVariable String raisonSocial) {
		return stageService.findByOrganismeAccueilRaisonSocial(raisonSocial);
	}
	@PostMapping("/")
	public int save(@RequestBody Stage stage) {
		return stageService.save(stage);
	}
	@GetMapping("/id/{id}")
	public Stage findByid(@PathVariable Long id) {
		return stageService.findByid(id);
	}
	@PutMapping("/")
	public int update(@RequestBody Stage stage) {
		return stageService.update(stage);
	}
	@DeleteMapping("/reference/{reference}")
	public int removeByReference(@PathVariable String reference) {
		return stageService.removeByReference(reference);
	}
	@GetMapping("/")
	public List<Stage> findAll() {
		return stageService.findAll();
	}
	@GetMapping("/reference/{reference}")
	public Stage findByReference(String reference) {
		return stageService.findByReference(reference);
	}
	
}
