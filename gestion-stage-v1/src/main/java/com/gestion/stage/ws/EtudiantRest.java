package com.gestion.stage.ws;

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

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.service.EtudiantService;

@RestController
@RequestMapping("gestion-stage-api/etudiant")
@CrossOrigin({"http://localhost:4200"})
public class EtudiantRest {
	@Autowired
	private EtudiantService etudiantService;
	@GetMapping("/cin/{cin}")
	public Etudiant findByCin(@PathVariable String cin) {
		return etudiantService.findByCin(cin);
	}
	@GetMapping("/codeAppoge/{codeAppoge}")
	public Etudiant findByCodeAppoge(@PathVariable String codeAppoge) {
		return etudiantService.findByCodeAppoge(codeAppoge);
	}

	public List<Etudiant> findByFiliere(Filiere filiere) {
		return etudiantService.findByFiliere(filiere);
	}
	@PostMapping("/")
	public int save(@RequestBody Etudiant etudiant) {
		return etudiantService.save(etudiant);
	}
	@GetMapping("/")
	public List<Etudiant> findAll() {
		return etudiantService.findAll();
	}
	@PutMapping("/")
	public int Update(@RequestBody Etudiant etudiant) {
		return etudiantService.Update(etudiant);
	}
	@DeleteMapping("/cin/{cin}")
	public int removeByCin(@PathVariable String cin) {
		return etudiantService.removeByCin(cin);
	}
	
}
