package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.CommentaireDocument;
import com.gestion.stage.bean.Document;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.CommentaireDocumentDao;
import com.gestion.stage.service.facade.CommentaireDocumentService;
import com.gestion.stage.service.facade.DocumentService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class CommentaireDocumentServiceImpl implements CommentaireDocumentService {
	@Autowired
	private CommentaireDocumentDao commentaireDocumentDao;

	@Autowired
	private UserService userService;

	@Autowired
	private DocumentService documentService;

	@Override
	public List<CommentaireDocument> findByUserId(Long id) {
		return commentaireDocumentDao.findByUserId(id);
	}

	@Override
	public List<CommentaireDocument> findByDocumentId(Long id) {
		return commentaireDocumentDao.findByDocumentId(id);
	}

	@Override
	public int save(CommentaireDocument commentaireDocument) {
		Document doc = documentService.findByReference(commentaireDocument.getDocument().getReference());
		User user = userService.findByReference(commentaireDocument.getUser().getReference());
		if (user == null || doc == null) {
			return -1;
		} else {
			commentaireDocument.setUser(user);
			commentaireDocument.setDocument(doc);
			commentaireDocument.setDateCreation(DateUtil.getDate());
			commentaireDocumentDao.save(commentaireDocument);
			return 1;
		}
	}

	@Override
	public int update(CommentaireDocument commentaireDocument) {
		Document doc = documentService.findByReference(commentaireDocument.getDocument().getReference());
		User user = userService.findByReference(commentaireDocument.getUser().getReference());
		if (user == null || doc == null) {
			return -1;
		} else {
			commentaireDocument.setUser(user);
			commentaireDocument.setDocument(doc);
			commentaireDocument.setDateModification(DateUtil.getDate());
			commentaireDocumentDao.save(commentaireDocument);
			return 1;
		}
	}

	@Override
	public int delete(Long id) {
		CommentaireDocument cd = commentaireDocumentDao.findById(id).get();
		commentaireDocumentDao.delete(cd);
		return 1;
	}

}
