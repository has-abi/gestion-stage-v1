package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Rapport;
import com.gestion.stage.service.facade.RapportService;
import com.gestion.stage.utils.ResponseMessage;

@RestController
@RequestMapping("gestion-stage-api/rapport")
@CrossOrigin({"http://localhost:4200"})
public class RapportRest {
	@Autowired
	private RapportService rapportService;
	@PutMapping("/")
	public ResponseEntity<ResponseMessage> updateRapport(@RequestParam("titre") String titre,@RequestParam("desc") String description,@RequestParam("ref") String ref,
			@RequestParam("file")	MultipartFile file) {
		return rapportService.updateRapport(titre, description, ref, file);
	}
	@PutMapping("/reference")
	public int validerRapport(@PathVariable  String ref) {
		return rapportService.validerRapport(ref);
	}

	public List<Rapport> findByDateDepot(Date dateDepot) {
		return rapportService.findByDateDepot(dateDepot);
	}

	public List<Rapport> findByDateSoutenance(Date dateSoutenance) {
		return rapportService.findByDateSoutenance(dateSoutenance);
	}

	public List<Rapport> findByDescreption(String descreption) {
		return rapportService.findByDescreption(descreption);
	}
	@PostMapping("/")
	public ResponseEntity<ResponseMessage> save(@RequestParam("titre") String titre,@RequestParam("desc") String description,@RequestParam("stageRef") String StageRef,@RequestParam("file") MultipartFile file) {
		return rapportService.save(titre, description, StageRef, file);
	}
	@GetMapping("/")
	public List<Rapport> findAll() {
		return rapportService.findAll();
	}
	@GetMapping("/reference/{reference}")
	public Rapport findByReference(@PathVariable String reference) {
		return rapportService.findByReference(reference);
	}
	
}
