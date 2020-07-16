package com.gestion.stage.rest;

import java.util.Date;
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

import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.service.facade.SujetForumService;
import com.sipios.springsearch.anotation.SearchSpec;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/sujetForum")
@CrossOrigin({ "http://localhost:4200" })
public class SujetForumRest {
	@Autowired
	private SujetForumService sujetForumService;

	@GetMapping("/count")
	public int countSujets() {
		return sujetForumService.countSujets();
	}

	@PutMapping("/")
	public int update(@RequestBody SujetForum sujetForum) {
		return sujetForumService.update(sujetForum);
	}

	@GetMapping("/content/{content}")
	public List<SujetForum> findByContentContains(@PathVariable String content) {
		return sujetForumService.findByContentContains(content);
	}

	@GetMapping("/user/id/{id}")
	public List<SujetForum> findByUserId(@PathVariable Long id) {
		return sujetForumService.findByUserId(id);
	}

	@GetMapping("/page/{page}/size/{size}/sort/{sort}")
	public Page<SujetForum> findAllWithPagination(@PathVariable int page, @PathVariable int size,
			@PathVariable String sort) {
		return sujetForumService.findAllWithPagination(page, size, sort);
	}

	@GetMapping("/search")
	public ResponseEntity<List<SujetForum>> searchForSujetForums(@SearchSpec Specification<SujetForum> spec) {
		return sujetForumService.searchForSujetForums(spec);
	}

	@PostMapping("/")
	public int save(@RequestBody SujetForum sujetForum) {
		return sujetForumService.save(sujetForum);
	}

	@GetMapping("/")
	public List<SujetForum> findAll() {
		return sujetForumService.findAll();
	}

	@GetMapping("/dateCreation/{dateCreation}")
	public List<SujetForum> findByDateCreation(@PathVariable Date dateCreation) {
		return sujetForumService.findByDateCreation(dateCreation);
	}

	@GetMapping("/dateModification/{dateModification}")
	public List<SujetForum> findByDateModification(@PathVariable Date dateModification) {
		return sujetForumService.findByDateModification(dateModification);
	}

	@DeleteMapping("/remove/reference/{reference}")
	public int remove(@PathVariable String reference) {
		return sujetForumService.remove(reference);
	}

	@GetMapping("/reference/{reference}")
	public SujetForum findByReference(@PathVariable String reference) {
		return sujetForumService.findByReference(reference);
	}

}
