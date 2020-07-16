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
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.utils.AppFilters;
import com.sipios.springsearch.anotation.SearchSpec;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/stage")
@CrossOrigin({ "http://localhost:4200" })
public class StageRest {

	@Autowired
	private StageService stageService;

	@GetMapping("/etudiant/id/{id}")
	public Stage findEtudiantActiveStage(@PathVariable Long id) {
		return stageService.findEtudiantActiveStage(id);
	}

	@GetMapping("/jury/id/{id}")
	public List<Stage> findJuryActiveStages(@PathVariable Long id) {
		return stageService.findJuryActiveStages(id);
	}

	@GetMapping("/coordinateur/id/{id}")
	public List<Stage> findCoordinateurActiveStages(@PathVariable Long id) {
		return stageService.findCoordinateurActiveStages(id);
	}

	@GetMapping("/encadreur/id/{id}")
	public List<Stage> findEncadreurActiveStages(@PathVariable Long id) {
		return stageService.findEncadreurActiveStages(id);
	}

	@GetMapping("/count")
	public int countStages() {
		return stageService.countStages();
	}

	@GetMapping("/encadreur/id/{id}/page/{page}/size/{size}")
	public Page<Stage> findByEncadreur(@PathVariable Long id, @PathVariable int page, @PathVariable int size) {
		return stageService.findByEncadreur(id, page, size);
	}

	@GetMapping("/jury/id/{id}/page/{page}/size/{size}")
	public Page<Stage> findByJury(@PathVariable Long id, @PathVariable int page, @PathVariable int size) {
		return stageService.findByJury(id, page, size);
	}

	@GetMapping("/coordinateur/count/ref/{reference}")
	public Long countByCoordinateurReference(@PathVariable String reference) {
		return stageService.countByCoordinateurReference(reference);
	}

	@GetMapping("/etudiant/count/id/{id}")
	public Long countByEtudiant(@PathVariable Long id) {
		return stageService.countByEtudiant(id);
	}

	@GetMapping("/encadreur/count/id/{id}")
	public Long countByEncadreur(@PathVariable Long id) {
		return stageService.countByEncadreur(id);
	}

	@GetMapping("/jury/count/id/{id}")
	public Long countByJury(@PathVariable Long id) {
		return stageService.countByJury(id);
	}

	@GetMapping("/etudiant/id/{id}/page/{page}/size/{size}")
	public Page<Stage> findByEtudiant(@PathVariable Long id, @PathVariable int page, @PathVariable int size) {
		return stageService.findByEtudiant(id, page, size);
	}

	@GetMapping("/coordinateur/id/{id}/page/{page}/size/{size}/sort/{sort}")
	public Page<Stage> findByCoordinateurUserId(@PathVariable Long id, @PathVariable int page, @PathVariable int size,
			@PathVariable String sort) {
		return stageService.findByCoordinateurUserId(id, page, size, sort);
	}

	@PutMapping("/activer")
	public int activerStage(@RequestBody String ref) {
		return stageService.activerStage(ref);
	}

	@GetMapping("/coord/id/{id}/search")
	public List<Stage> searchByCoord(@SearchSpec Specification<Stage> spec, @PathVariable Long id) {
		ResponseEntity<List<Stage>> stgs = stageService.searchForStages(spec);
		return AppFilters.filterStagesByCoord(id, stgs.getBody());
	}

	@GetMapping("/encadreur/id/{id}/search")
	public List<Stage> searchByEncadreur(@SearchSpec Specification<Stage> spec, @PathVariable Long id) {
		ResponseEntity<List<Stage>> stgs = stageService.searchForStages(spec);
		return AppFilters.filterStagesByEncadreur(id, stgs.getBody());
	}

	@GetMapping("/etudiant/id/{id}/search")
	public List<Stage> searchByEtudiant(@SearchSpec Specification<Stage> spec, @PathVariable Long id) {
		ResponseEntity<List<Stage>> stgs = stageService.searchForStages(spec);
		return AppFilters.filterStagesByEtudiant(id, stgs.getBody());
	}

	@GetMapping("/jury/id/{id}/search")
	public List<Stage> searchByJury(@SearchSpec Specification<Stage> spec, @PathVariable Long id) {
		ResponseEntity<List<Stage>> stgs = stageService.searchForStages(spec);
		return AppFilters.filterStagesByJury(id, stgs.getBody());
	}

	@GetMapping("/search")
	public ResponseEntity<List<Stage>> search(@SearchSpec Specification<Stage> spec) {
		return stageService.searchForStages(spec);

	}

	@RequestMapping(value = "/list/get", params = { "page", "size" }, method = RequestMethod.GET)
	public Page<Stage> findAllWithPaginition(@RequestParam("page") int page, @RequestParam("size") int size) {
		return stageService.findAllWithPaginition(page, size);
	}

	@GetMapping("/dateDebut/{dateDebut}/page/{page}/size/{size}")
	public Page<Stage> findByDateDebut(@PathVariable String dateDebut, @PathVariable int page, @PathVariable int size) {
		return stageService.findByDateDebut(dateDebut, page, size);
	}

	@GetMapping("/dateFin/{dateFin}/page/{page}/size/{size}")
	public Page<Stage> findByDateFin(@PathVariable String dateFin, @PathVariable int page, @PathVariable int size) {
		return stageService.findByDateFin(dateFin, page, size);
	}

	@GetMapping("/dateFin1/{date1}/dateFin2/{date2}")
	public List<Stage> findByDateFinBetween(@PathVariable String date1, @PathVariable String date2) {
		return stageService.findByDateFinBetween(date1, date2);
	}

	@GetMapping("/sujet/{sujet}/page/{page}/size/{size}")
	public Page<Stage> findBySujetContains(@PathVariable String sujet, @PathVariable int page, @PathVariable int size) {
		return stageService.findBySujetContains(sujet, page, size);
	}

	@GetMapping("/organismeAccueil/{raisonSocial}/page/{page}/size/{size}")
	public Page<Stage> findByOrganismeAccueilRaisonSocial(@PathVariable String raisonSocial, @PathVariable int page,
			@PathVariable int size) {
		return stageService.findByOrganismeAccueilRaisonSocial(raisonSocial, page, size);
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
