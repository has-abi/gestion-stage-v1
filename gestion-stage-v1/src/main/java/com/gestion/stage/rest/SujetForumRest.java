package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.service.SujetForumService;

@RestController
@RequestMapping("/stage/sujetForum")
public class SujetForumRest {
@Autowired
private SujetForumService sujetForumService;

@PostMapping("/")
public int save(SujetForum sujetForum) {
	return sujetForumService.save(sujetForum);
}
@GetMapping("/")
public List<SujetForum> findAll() {
	return sujetForumService.findAll();
}

@GetMapping("/dateCreation/{dateCreation}")
public List<SujetForum> findByDateCreation(Date dateCreation) {
	return sujetForumService.findByDateCreation(dateCreation);
}

@GetMapping("/dateModification/{dateModification}")
public List<SujetForum> findByDateModification(Date dateModification) {
	return sujetForumService.findByDateModification(dateModification);
}
@DeleteMapping("/suejetForum/{sujetForum}")
public int remove(SujetForum sujetForum) {
	return sujetForumService.remove(sujetForum);
}
@GetMapping("/reference/{reference}")
public SujetForum findByReference(String reference) {
	return sujetForumService.findByReference(reference);
}

}
