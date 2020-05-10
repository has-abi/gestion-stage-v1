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

import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.service.StageEtudiantService;

@RestController
@RequestMapping("gestion-stage-api/stage-etudiant")
@CrossOrigin({"http://localhost:4200"})
public class StageEtudiantRest {
	@Autowired
	private StageEtudiantService stageEtudiantService;
	@GetMapping("/stage/reference/{reference}")
	public List<StageEtudiant> findByStageReference(@PathVariable String reference) {
		return stageEtudiantService.findByStageReference(reference);
	}
	@GetMapping("/etudiant/cin/{cin}")
	public List<StageEtudiant> findByEtudiantCin(@PathVariable String cin) {
		return stageEtudiantService.findByEtudiantCin(cin);
	}
	@GetMapping("/")
	public List<StageEtudiant> findAll() {
		return stageEtudiantService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody StageEtudiant stageEtudiant) {
		return stageEtudiantService.save(stageEtudiant);
	}
	@PutMapping("/")
	public int update(@RequestBody StageEtudiant stageEtudiant) {
		return stageEtudiantService.update(stageEtudiant);
	}
	@DeleteMapping("/id/{id}")
	public int removeById(@PathVariable Long id) {
		return stageEtudiantService.removeById(id);
	}
	@GetMapping("/stage/reference/{reference}/etudiant/cin/{cin}")
	public StageEtudiant findByStageReferenceAndEtudiantCin(@PathVariable String refernce,@PathVariable String cin) {
		return stageEtudiantService.findByStageReferenceAndEtudiantCin(refernce, cin);
	}
	
}
