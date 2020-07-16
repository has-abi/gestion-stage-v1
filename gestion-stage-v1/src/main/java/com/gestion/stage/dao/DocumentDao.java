package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Document;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface DocumentDao extends JpaRepository<Document, Long> {
	Document findByTitre(String titre);

	Document findByReference(String reference);
}
