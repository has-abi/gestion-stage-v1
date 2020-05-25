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

import com.gestion.stage.bean.TypeDocument;
import com.gestion.stage.service.facade.TypeDocumentService;

@RestController
@RequestMapping("/stage/typeDocument")
public class TypeDocumentRest {
	@Autowired
	private TypeDocumentService typeDocumentService;
@GetMapping("/ref/{ref}")
	public TypeDocument findByRef(@PathVariable String ref) {
		return typeDocumentService.findByRef(ref);
	}
@DeleteMapping("/delete/ref/{ref}")
	public int deleteByRef(@PathVariable String ref) {
		return typeDocumentService.deleteByRef(ref);
	}

@PostMapping("/")
	public int save( @RequestBody TypeDocument typeDocument) {
		return typeDocumentService.save(typeDocument);
	}
@GetMapping()
	public List<TypeDocument> findAll() {
		return typeDocumentService.findAll();
	}
	

}
