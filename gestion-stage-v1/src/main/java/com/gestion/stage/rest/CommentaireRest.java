package com.gestion.stage.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.service.facade.CommentaireService;

@RestController
@RequestMapping("gestion-stage-api/commentaire")
@CrossOrigin({"http://localhost:4200"})
public class CommentaireRest {

	@Autowired
	private CommentaireService commentaireService;
	
	@GetMapping("/user/id/{id}")
	public List<Commentaire> findByUserId(Long id) {
		return commentaireService.findByUserId(id);
	}
	@GetMapping("/commentaire/id/{id}")
	public List<Commentaire> findbyCommentaireId(Long id) {
		return commentaireService.findbyCommentaireId(id);
	}
	@PutMapping("/")
	public int updateCommentaire(@RequestBody Commentaire commentaire) {
		return commentaireService.updateCommentaire(commentaire);
	}
	@DeleteMapping("/id/{id}")
	public int deleteById(@PathVariable Long id) {
		return commentaireService.deleteById(id);
	}

	@GetMapping("/dateCreation/{dateCreation}")
	public List<Commentaire> findByDateCreation(@PathVariable Date dateCreation) {
		return commentaireService.findByDateCreation(dateCreation);
	}

	@GetMapping("/sujetForum/id/{id}")
	public List<Commentaire> findBySujetForumId(@PathVariable Long id) {
		return commentaireService.findBySujetForumId(id);
	}

	@PostMapping("/")
	public int save(@RequestBody Commentaire commentaire) {
		return commentaireService.save(commentaire);
	}

	@GetMapping("/")
	public List<Commentaire> findAll() {
		return commentaireService.findAll();
	}

}
