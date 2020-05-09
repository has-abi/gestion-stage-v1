package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.TypeDocument;

@Repository
public interface TypeDocumentDao extends JpaRepository<TypeDocument, Long> {
	TypeDocument findByRef(String ref);
	int deleteByRef(String ref);

	
	

}
