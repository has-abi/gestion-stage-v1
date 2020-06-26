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
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.service.facade.EtudiantService;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("gestion-stage-api/etudiant")
@CrossOrigin({ "http://localhost:4200" })
public class EtudiantRest {
	
	@Autowired
	private EtudiantService etudiantService;
	
	@GetMapping("/confirm/cne/{cne}/codeAppoge/{codeAppoge}")
	public int validateEtudiant(@PathVariable String cne,@PathVariable String codeAppoge) {
		return etudiantService.validateEtudiant(cne, codeAppoge);
	}

	@GetMapping("/encadreur/id/{id}")
	public List<Etudiant> findByEncadreurid(@PathVariable Long id) {
		return etudiantService.findByEncadreurid(id);
	}

	@GetMapping("/jury/id/{id}")
	public List<Etudiant> findByJuryId(@PathVariable Long id) {
		return etudiantService.findByJuryId(id);
	}

	@GetMapping("/count")
	public int countEtdudiants() {
		return etudiantService.countEtdudiants();
	}

	@GetMapping("/filiere/id/{id}")
	public List<Etudiant> findByFiliere(@PathVariable Long id) {
		return etudiantService.findByFiliere(id);
	}

	@GetMapping("/coordinateur/id/{id}/page/{page}/size/{size}/sort/{sort}")
	public Page<Etudiant> findByCoordinateur(@PathVariable long id, @PathVariable int page, @PathVariable int size,
			@PathVariable String sort) {
		return etudiantService.findByCoordinateur(id, page, size, sort);
	}

	@GetMapping("/user/nom/{nom}/prenom/{prenom}/page/{page}/size/{size}")
	public Page<Etudiant> findByUserNomContainsOrUserPrenomContains(@PathVariable String nom,
			@PathVariable String prenom, @PathVariable int page, @PathVariable int size) {
		return etudiantService.findByUserNomContainsOrUserPrenomContains(nom, prenom, page, size);
	}

	@GetMapping("/niveau/{niveau}/page/{page}/size/{size}")
	public Page<Etudiant> findByNiveau(@PathVariable String niveau, @PathVariable int page, @PathVariable int size) {
		return etudiantService.findByNiveau(niveau, page, size);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Etudiant>> searchForEtudiants(@SearchSpec Specification<Etudiant> spec) {
		return etudiantService.searchForEtudiants(spec);
	}

	@GetMapping("/page/{page}/size/{size}")
	public Page<Etudiant> findAllWithPaginition(@PathVariable int page, @PathVariable int size) {
		return etudiantService.findAllWithPaginition(page, size);
	}

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

	@GetMapping("/page/{page}/size/{size}/sort/{sort}")
	public Page<Etudiant> findAll(@PathVariable int page, @PathVariable int size, @PathVariable String sort) {
		return etudiantService.findAll(page, size, sort);
	}

	@PutMapping("/")
	public int Update(@RequestBody Etudiant etudiant) {
		return etudiantService.Update(etudiant);
	}

	@DeleteMapping("/cin/{cin}")
	public int removeByCin(@PathVariable String cin) {
		return etudiantService.removeByCin(cin);
	}

	@GetMapping("/user/id/{id}")
	public Etudiant findByUserId(@PathVariable Long id) {
		return etudiantService.findByUserId(id);
	}

}
