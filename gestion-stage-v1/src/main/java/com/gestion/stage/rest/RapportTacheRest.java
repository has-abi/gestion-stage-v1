package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.RapportTache;
import com.gestion.stage.service.facade.RapportTacheService;
import com.gestion.stage.utils.ResponseMessage;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/rapportTache")
@CrossOrigin({ "http://localhost:4200" })
public class RapportTacheRest {
	@Autowired
	private RapportTacheService rapportTacheService;

	@GetMapping("/")
	public List<RapportTache> findAll() {
		return rapportTacheService.findAll();
	}

	@PostMapping("/save")
	public ResponseEntity<ResponseMessage> save(@RequestParam("titre") String titre, @RequestParam("desc") String desc,
			@RequestParam("ref") String ref, @RequestParam("file") MultipartFile file) {
		System.out.println(ref);
		return rapportTacheService.save(titre, desc, ref, file);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseMessage> update(@RequestParam("titre") String titre,
			@RequestParam("desc") String desc, @RequestParam("ref") String ref, MultipartFile file) {
		return rapportTacheService.update(titre, desc, ref, file);
	}

	@DeleteMapping("/id/{id}/tache/ref/{tache}")
	public int delete(@PathVariable long id, @PathVariable String tacheRef) {
		return rapportTacheService.delete(id, tacheRef);
	}

	@GetMapping("/reference/{reference}")
	public RapportTache findByReference(@PathVariable String reference) {
		return rapportTacheService.findByReference(reference);
	}

}
