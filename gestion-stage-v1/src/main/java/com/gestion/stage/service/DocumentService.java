package com.gestion.stage.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.gestion.stage.bean.Document;

public interface DocumentService {
	Document findByTitre(String titre);
	Document findByReference(String reference);
	int deleteByReference(String reference);
	int save(Document document);
	List<Document> findAll();
}
