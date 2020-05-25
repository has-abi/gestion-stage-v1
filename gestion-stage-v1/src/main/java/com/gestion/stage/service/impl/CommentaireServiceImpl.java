package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.dao.CommentaireDao;
import com.gestion.stage.service.facade.CommentaireService;
import com.gestion.stage.utils.Utils;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	private CommentaireDao commentaireDao;
	
	private Utils utils = new Utils();
	


	@Override
	public List<Commentaire> findBySujetForum(SujetForum sujetForum) {
		
		return commentaireDao.findBySujetForum(sujetForum);
	}

	@Override
	public int save(Commentaire commentaire) {
	List<Commentaire> commentaireFounded=findByDateCreation(commentaire.getDateCreation());
	if(commentaireFounded!=null) {return -1;}
	else {
		commentaireDao.save(commentaire);
		return 1 ;
	}
	
		
	}

	@Override
	public List<Commentaire> findAll() {
		return  commentaireDao.findAll();
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
		List<Commentaire> commentaires=findByDateCreation(dateCreation);
		if(commentaires==null) {
			return -1;
		}else {
			for (Commentaire commentaire : commentaires) {
				
				commentaireDao.delete(commentaire);
			}
				return 1;
			}
	
	}

	@Override
	public int updateCommentaire(Commentaire commentaire) {
		List<Commentaire> commentaireFounded=findByDateCreation(commentaire.getDateCreation());
		if(commentaireFounded!=null) 
		{return -1;
		}
		
		else {
			commentaireDao.save(commentaire);
			return 1 ;

	
	}
	}}

