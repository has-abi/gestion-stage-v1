package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.CommentaireDocument;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface CommentaireDocumentDao extends JpaRepository<CommentaireDocument, Long> {

	List<CommentaireDocument> findByUserId(Long id);

	List<CommentaireDocument> findByDocumentId(Long id);

}
