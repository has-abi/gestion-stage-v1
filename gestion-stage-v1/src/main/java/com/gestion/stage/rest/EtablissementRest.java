package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.service.facade.EtablissementService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/etablissement")
@CrossOrigin({ "http://localhost:4200" })
public class EtablissementRest {
	@Autowired
	private EtablissementService etablissementService;

	@GetMapping("/")
	public List<Etablissement> findAll() {
		return etablissementService.findAll();
	}

	@PostMapping("/")
	public int save(@RequestBody Etablissement etablissement) {
		return etablissementService.save(etablissement);
	}

	@PutMapping("/")
	public int update(@RequestBody Etablissement etablissement) {
		return etablissementService.update(etablissement);
	}

}
