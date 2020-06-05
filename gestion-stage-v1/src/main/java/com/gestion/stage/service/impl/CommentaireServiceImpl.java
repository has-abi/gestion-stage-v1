package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.CommentaireDao;
import com.gestion.stage.service.facade.CommentaireService;
import com.gestion.stage.service.facade.SujetForumService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.Utils;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	private CommentaireDao commentaireDao;
	@Autowired
	private UserService userService;
	@Autowired
	private SujetForumService sujetForumService;

	private Utils utils = new Utils();

	@Override
	public int save(Commentaire commentaire) {
		User u = userService.findByEmail(commentaire.getUser().getEmail());
		SujetForum sf = sujetForumService.findByReference(commentaire.getSujetForum().getReference());
		if (u == null || sf == null) {
			return -1;
		} else {
			commentaire.setUser(u);
			commentaire.setSujetForum(sf);
			commentaire.setDateCreation(DateUtil.getDate());
			commentaireDao.save(commentaire);
			return 1;
		}

	}

	@Override
	public List<Commentaire> findAll() {
		return commentaireDao.findAll();
	}

	@Override
	public List<Commentaire> findByDateCreation(Date dateCreation) {
		if (utils.checkDate(dateCreation) > 0) {
			return commentaireDao.findByDateCreation(dateCreation);
		} else {
			return null;
		}
	}

	@Override
	public int deleteByDateCreation(Date dateCreation) {
		List<Commentaire> commentaires = findByDateCreation(dateCreation);
		if (commentaires == null) {
			return -1;
		} else {
			for (Commentaire commentaire : commentaires) {

				commentaireDao.delete(commentaire);
			}
			return 1;
		}

	}

	@Override
	public int updateCommentaire(Commentaire commentaire) {
		Commentaire commentaireFounded = commentaireDao.findById(commentaire.getId()).get();
		if (commentaireFounded != null) {
			return -1;
		} else {
			commentaire.setDateModification(DateUtil.getDate());
			commentaireDao.save(commentaire);
			return 1;
		}
	}

	@Override
	public List<Commentaire> findBySujetForumId(long id) {
		return commentaireDao.findBySujetForumId(id);
	}

	@Override
	public List<Commentaire> findByUserId(Long id) {
		return findByUserId(id);
	}

	@Override
	public List<Commentaire> findbyCommentaireId(Long id) {
		return commentaireDao.findByCommentaireId(id);
	}

	@Override
	public int deleteById(Long id) {
		Commentaire foundedCommentaire = commentaireDao.findById(id).get();
		List<Commentaire> relatedComments = findbyCommentaireId(id);
		relatedComments.forEach(c -> commentaireDao.delete(c));
		commentaireDao.delete(foundedCommentaire);
		return 1;
	}
}
