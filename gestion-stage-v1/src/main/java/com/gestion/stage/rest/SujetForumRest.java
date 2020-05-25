package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.service.facade.SujetForumService;

@RestController
@RequestMapping("/stage/sujetForum")
public class SujetForumRest {
@Autowired
private SujetForumService sujetForumService;

@PostMapping("/")
public int save( @RequestBody SujetForum sujetForum) {
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
public List<SujetForum> findByDateModification( @PathVariable Date dateModification) {
	return sujetForumService.findByDateModification(dateModification);
}
@DeleteMapping("/suejetForum/{sujetForum}")
public int remove( @RequestBody SujetForum sujetForum) {
	return sujetForumService.remove( sujetForum);
}
@GetMapping("/reference/{reference}")
public SujetForum findByReference( @PathVariable String reference) {
	return sujetForumService.findByReference(reference);
}

}
