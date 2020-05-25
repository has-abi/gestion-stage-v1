package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.TypeServiceOrganisme;
import com.gestion.stage.service.facade.TypeServiceOrganismeService;

@RestController
@RequestMapping("gestion-stage-api/typeServiceOrganisme")
@CrossOrigin({"http://localhost:4200"})
public class TypeOrganismesServiceRest {
	@Autowired
	private TypeServiceOrganismeService typeServiceOrganismeService;
	@GetMapping("/type/{type}")
	public TypeServiceOrganisme findByType(String type) {
		return typeServiceOrganismeService.findByType(type);
	}
	@GetMapping("/")
	public List<TypeServiceOrganisme> findAll() {
		return typeServiceOrganismeService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody TypeServiceOrganisme typeServiceOrganisme) {
		return typeServiceOrganismeService.save(typeServiceOrganisme);
	}
	@PutMapping("/")
	public int update(@RequestBody TypeServiceOrganisme typeServiceOrganisme) {
		return typeServiceOrganismeService.update(typeServiceOrganisme);
	}
	@DeleteMapping("/type/{type}")
	public int removeByType(String type) {
		return typeServiceOrganismeService.removeByType(type);
	}
}
