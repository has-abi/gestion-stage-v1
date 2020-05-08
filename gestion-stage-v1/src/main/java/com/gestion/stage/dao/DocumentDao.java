package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.gestion.stage.bean.Document;

@Repository
public interface DocumentDao extends JpaRepository<Document, Long>{
	Document findByTitre(String titre);
	Document findByReference(String reference);
	int deleteByReference(String reference);
	
	

}
