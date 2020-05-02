package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Document;

@Repository
public interface DocumentDao extends JpaRepository<Document, Long>{
	List<Document> findByTypeDoc(String typeDoc);
	Document findByTitre(String titre);
	

}
