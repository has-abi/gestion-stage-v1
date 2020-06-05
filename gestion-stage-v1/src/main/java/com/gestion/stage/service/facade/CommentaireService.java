package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.Commentaire;

public interface CommentaireService {
    List<Commentaire> findByDateCreation(Date dateCreation);
    List<Commentaire> findBySujetForumId(long id);
    List<Commentaire> findByUserId(Long id);
    List<Commentaire> findbyCommentaireId(Long id);
    int save(Commentaire commentaire);
    List<Commentaire> findAll();
    int deleteByDateCreation(Date dateCreation);
    int updateCommentaire(Commentaire commentaire);
    int deleteById(Long id);

}
