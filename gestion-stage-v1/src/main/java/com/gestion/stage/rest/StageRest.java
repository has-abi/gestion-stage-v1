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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.service.StageService;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("gestion-stage-api/stage")
@CrossOrigin({"http://localhost:4200"})
public class StageRest {
	 
	@Autowired
	private StageService stageService;
	@GetMapping("/search")
	 public ResponseEntity<List<Stage>> searchForStages(@SearchSpec Specification<Stage> spec) {
		return stageService.searchForStages(spec);
	}
	@RequestMapping(
      		value = "/list/get", 
      		params = { "page", "size" }, 
      		method = RequestMethod.GET
   		 )
	public Page<Stage> findAllWithPaginition(@RequestParam("page") int page,@RequestParam("size") int size) {
		return stageService.findAllWithPaginition(page, size);
	}
	@GetMapping("/dateDebut/{dateDebut}/page/{page}/size/{size}")
	public Page<Stage> findByDateDebut(@PathVariable String dateDebut,@PathVariable int page,@PathVariable int size) {
		return stageService.findByDateDebut(dateDebut,page,size);
	}
	@GetMapping("/dateFin/{dateFin}/page/{page}/size/{size}")
	public Page<Stage> findByDateFin(@PathVariable  String dateFin,@PathVariable int page,@PathVariable int size) {
		return stageService.findByDateFin(dateFin,page,size);
	}
	
	@GetMapping("/dateFin1/{date1}/dateFin2/{date2}")
	public List<Stage> findByDateFinBetween(@PathVariable String date1,@PathVariable String date2) {
		return stageService.findByDateFinBetween(date1, date2);
	}
	@GetMapping("/sujet/{sujet}/page/{page}/size/{size}")
	public Page<Stage> findBySujetContains(@PathVariable String sujet,@PathVariable int page,@PathVariable int size) {
		return stageService.findBySujetContains(sujet,page,size);
	}
	@GetMapping("/organismeAccueil/{raisonSocial}/page/{page}/size/{size}")
	public Page<Stage> findByOrganismeAccueilRaisonSocial(@PathVariable String raisonSocial,@PathVariable int page,@PathVariable int size) {
		return stageService.findByOrganismeAccueilRaisonSocial(raisonSocial,page,size);
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
