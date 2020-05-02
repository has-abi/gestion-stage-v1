package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;

public interface CommentaireService {
	List<Commentaire> findByCommentaire(Commentaire commentaire);
    List<Commentaire> findByContenu(String contenu);
    List<Commentaire> findByDateCreation(Date dateCreation);
     List<Commentaire> findBySujetForum(SujetForum sujetForum);
    int save(Commentaire commentaire);
    List<Commentaire> findAll();


}
