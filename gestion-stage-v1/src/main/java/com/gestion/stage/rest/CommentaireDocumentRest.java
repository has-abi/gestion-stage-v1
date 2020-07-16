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

import com.gestion.stage.bean.CommentaireDocument;
import com.gestion.stage.service.facade.CommentaireDocumentService;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("gestion-stage-api/commentaireDocument")
@CrossOrigin({ "http://localhost:4200" })
public class CommentaireDocumentRest {

	@Autowired
	private CommentaireDocumentService commentaireDocumentService;

	@GetMapping("/user/id/{id}")
	public List<CommentaireDocument> findByUserId(@PathVariable Long id) {
		return commentaireDocumentService.findByUserId(id);
	}

	@GetMapping("/document/id/{id}")
	public List<CommentaireDocument> findByDocumentId(@PathVariable Long id) {
		return commentaireDocumentService.findByDocumentId(id);
	}

	@PostMapping("/")
	public int save(@RequestBody CommentaireDocument commentaireDocument) {
		return commentaireDocumentService.save(commentaireDocument);
	}

	@PutMapping("/")
	public int update(@RequestBody CommentaireDocument commentaireDocument) {
		return commentaireDocumentService.update(commentaireDocument);
	}

	@DeleteMapping("/id/{id}")
	public int delete(@PathVariable Long id) {
		return commentaireDocumentService.delete(id);
	};
}
