package com.gestion.stage.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Document;
import com.gestion.stage.service.facade.DocumentService;
import com.gestion.stage.utils.ResponseMessage;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping("/stage/document")
@CrossOrigin({ "http://localhost:4200" })
public class DocumentRest {
	@Autowired
	private DocumentService documentService;

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> loadFile(@PathVariable String filename) {
		return documentService.loadFile(filename);
	}

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
	public ResponseEntity<ResponseMessage> save(@RequestParam String titre, @RequestParam("file") MultipartFile file) {
		return documentService.save(titre, file);
	}

	@GetMapping("/")
	public List<Document> findAll() {
		return documentService.findAll();
	}

}
