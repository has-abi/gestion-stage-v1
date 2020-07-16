package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.CommentaireDocument;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface CommentaireDocumentService {

	List<CommentaireDocument> findByUserId(Long id);

	List<CommentaireDocument> findByDocumentId(Long id);

	int save(CommentaireDocument commentaireDocument);

	int update(CommentaireDocument commentaireDocument);

	int delete(Long id);

}
