package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;

public interface CommentaireService {
    List<Commentaire> findByDateCreation(Date dateCreation);
     List<Commentaire> findBySujetForum(SujetForum sujetForum);
    int save(Commentaire commentaire);
    List<Commentaire> findAll();
    int deleteByDateCreation(Date dateCreation);
    int updateCommentaire(Commentaire commentaire);


}
