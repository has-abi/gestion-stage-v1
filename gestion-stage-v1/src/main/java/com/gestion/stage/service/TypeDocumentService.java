package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.TypeDocument;

public interface TypeDocumentService {
	TypeDocument findByRef(String ref);
	int deleteByRef(String ref);
	int save(TypeDocument typeDocument);
	List<TypeDocument> findAll();


}
