package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Commentaire;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface CommentaireDao extends JpaRepository<Commentaire, Long> {
	List<Commentaire> findByDateCreation(Date dateCreation);

	List<Commentaire> findBySujetForumId(long id);

	List<Commentaire> findByUserId(Long id);

	List<Commentaire> findByCommentaireId(Long id);

	int deleteByDateCreation(Date dateCreation);

}
