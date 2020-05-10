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

import com.gestion.stage.bean.MembreJury;
import com.gestion.stage.service.MembreJuryService;

@RestController
@RequestMapping("gestion-stage-api/jury")
@CrossOrigin({"http://localhost:4200"})
public class MembreJuryRest {
	@Autowired
	private MembreJuryService membreJuryService;
	@GetMapping("/utilisateur/{id}")
	public MembreJury findByUtilisateurId(@PathVariable Long id) {
		return membreJuryService.findByUtilisateurId(id);
	}
	@GetMapping("/profession/{profession}")
	public List<MembreJury> findByProfession(@PathVariable String profession) {
		return membreJuryService.findByProfession(profession);
	}
	@GetMapping("/")
	public List<MembreJury> findAll() {
		return membreJuryService.findAll();
	}
	@GetMapping("/reference/{reference}")
	public MembreJury findByReference(@PathVariable String reference) {
		return membreJuryService.findByReference(reference);
	}
	@PostMapping("/")
	public int save(@RequestBody MembreJury membreJury) {
		return membreJuryService.save(membreJury);
	}
	@PutMapping("/")
	public int update(@RequestBody MembreJury membreJury) {
		return membreJuryService.update(membreJury);
	}
	@DeleteMapping("/reference/{reference}")
	public int removeByReference(@PathVariable String reference) {
		return membreJuryService.removeByReference(reference);
	}
}
