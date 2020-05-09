package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stage.bean.Document;
import com.gestion.stage.service.DocumentService;

@RestController
@RequestMapping("/stage/document")
public class DocumentRest {
	@Autowired
	private DocumentService documentService;

	@GetMapping("/titre/{titre}")
	public Document findByTitre(@PathVariable String titre) {
		return documentService.findByTitre(titre);
	}

	@GetMapping("/reference/{reference}")
	public Document findByReference(@PathVariable String reference) {
		return documentService.findByReference(reference);
	}
@DeleteMapping("/delete/reference/{reference}")
	public int deleteByReference(@PathVariable String reference) {
		return documentService.deleteByReference(reference);
	}
@PostMapping("/")
	public int save( @RequestBody Document document) {
		return documentService.save(document);
	}
@GetMapping("/")
	public List<Document> findAll() {
		return documentService.findAll();
	}
	

}
