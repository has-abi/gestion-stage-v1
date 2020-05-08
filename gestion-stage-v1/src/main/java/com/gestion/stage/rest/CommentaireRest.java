package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.service.CommentaireService;

@RestController
@RequestMapping("/stage/commentaire")
public class CommentaireRest {

	@Autowired
	private CommentaireService commentaireService;
	@GetMapping("/dateCreation/{dateCreatuin}")
	public List<Commentaire> findByDateCreation(Date dateCreation) {
		return commentaireService.findByDateCreation(dateCreation);
	}
@GetMapping("/sujetForum/{sujetForum}")
	public List<Commentaire> findBySujetForum(SujetForum sujetForum) {
		return commentaireService.findBySujetForum(sujetForum);
	}
@PostMapping("/")
	public int save(Commentaire commentaire) {
		return commentaireService.save(commentaire);
	}

@GetMapping("/")
	public List<Commentaire> findAll() {
		return commentaireService.findAll();
	}

	

}
