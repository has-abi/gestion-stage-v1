package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.DocumentCommentaire;

public interface DocumentCommentaireService {
	List<DocumentCommentaire> findByDocumentCommentaire(DocumentCommentaire documentCommentaire);

	List<DocumentCommentaire> findByDateModification(Date dateModification);
	int save(DocumentCommentaire documentCommentaire);
	List<DocumentCommentaire> findAll();
	

}
