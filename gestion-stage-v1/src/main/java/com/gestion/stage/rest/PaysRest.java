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

import com.gestion.stage.bean.Pays;
import com.gestion.stage.service.PaysService;

@RestController
@RequestMapping("gestion-stage-api/pays")
@CrossOrigin({"http://localhost:4200"})
public class PaysRest {
	@Autowired
	private PaysService paysService;
	@PutMapping("/")
	public int update(@RequestBody Pays pays) {
		return paysService.update(pays);
	}
	@DeleteMapping("/nom/{nom}")
	public int removeByNom(@PathVariable String nom) {
		return paysService.removeByNom(nom);
	}
	@GetMapping("/nom/{nom}")
	public Pays findByNom(@PathVariable String nom) {
		return paysService.findByNom(nom);
	}
	@PostMapping("/")
	public int save(@RequestBody Pays pays) {
		return paysService.save(pays);
	}
	@GetMapping("/")
	public List<Pays> findAll() {
		return paysService.findAll();
	}
	
}
