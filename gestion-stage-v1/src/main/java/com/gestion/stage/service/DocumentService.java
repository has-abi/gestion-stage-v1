package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.Document;

public interface DocumentService {
	List<Document> findByTypeDoc(String typeDoc);
	Document findByTitre(String titre);
	int save(Document document);
	List<Document> findAll();
}
