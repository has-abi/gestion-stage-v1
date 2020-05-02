package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.DocumentCommentaire;

@Repository
public interface DocumentCommentaireDao extends JpaRepository<DocumentCommentaire, Long> {

	List<DocumentCommentaire> findByDocumentCommentaire(DocumentCommentaire documentCommentaire);

	List<DocumentCommentaire> findByDateModification(Date dateModification);
	

}
