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

import com.gestion.stage.service.TypeOrganismeService;
@RestController
@RequestMapping("gestion-stage-api/typeOrganisme")
@CrossOrigin({"http://localhost:4200"})
public class TypeOrganisme {
	@Autowired
	private TypeOrganismeService typeOrganismeService;
	@GetMapping("/type/{type}")
	public com.gestion.stage.bean.TypeOrganisme findByType(@PathVariable String type) {
		return typeOrganismeService.findByType(type);
	}
	@GetMapping("/")
	public List<com.gestion.stage.bean.TypeOrganisme> findAll() {
		return typeOrganismeService.findAll();
	}
	@PostMapping("/")
	public int save(@RequestBody com.gestion.stage.bean.TypeOrganisme typeOrganisme) {
		return typeOrganismeService.save(typeOrganisme);
	}
	@PutMapping("/")
	public int update(@RequestBody com.gestion.stage.bean.TypeOrganisme typeOrganisme) {
		return typeOrganismeService.update(typeOrganisme);
	}
	@DeleteMapping("/type/{type}")
	public int removeByType(@PathVariable String type) {
		return typeOrganismeService.removeByType(type);
	}
}
