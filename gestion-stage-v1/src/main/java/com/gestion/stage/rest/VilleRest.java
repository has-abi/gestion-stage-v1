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

import com.gestion.stage.bean.Ville;
import com.gestion.stage.service.facade.VilleService;
import com.gestion.stage.utils.VilleStatistics;

@RestController
@RequestMapping("gestion-stage-api/ville")
@CrossOrigin({"http://localhost:4200"})
public class VilleRest {
	@Autowired
	private VilleService villeService;
	@GetMapping("/statistics/filier/id/{id}")
	public List<VilleStatistics> numberorganismeByVille(@PathVariable Long id) {
		return villeService.numberorganismeByVille(id);
	}
	
	@GetMapping("/count")
	public int countVilles() {
		return villeService.countVilles();
	}
	@GetMapping("/id/{id}")
	public Ville findbyId(@PathVariable Long id) {
		return villeService.findbyId(id);
	}
	@GetMapping("/pays/{nom}")
	public List<Ville> findByPaysNom(@PathVariable String nom) {
		return villeService.findByPaysNom(nom);
	}
	@PostMapping("/")
	public int save(@RequestBody Ville ville) {
		return villeService.save(ville);
	}
	@DeleteMapping("id/{id}")
	public int removeByid(@PathVariable Long id) {
		return villeService.removeByid(id);
	}
	@PutMapping("/")
	public int update(@RequestBody Ville ville) {
		return villeService.update(ville);
	}
	@GetMapping("/")
	public List<Ville> findAll() {
		return villeService.findAll();
	}
	@GetMapping("pays/{pays}/nom/{nom}")
	public Ville findByPaysNomAndNom(@PathVariable String pays,@PathVariable String nom) {
		return villeService.findByPaysNomAndNom(pays, nom);
	}
	
	
}
