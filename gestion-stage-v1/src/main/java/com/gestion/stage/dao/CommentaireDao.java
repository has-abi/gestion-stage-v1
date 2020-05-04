package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;

@Repository
public interface CommentaireDao  extends JpaRepository<Commentaire, Long>{
	List<Commentaire> findByCommentaire(Commentaire commentaire);
     List<Commentaire> findByDateCreation(Date dateCreation);
      List<Commentaire> findBySujetForum(SujetForum sujetForum);
}
