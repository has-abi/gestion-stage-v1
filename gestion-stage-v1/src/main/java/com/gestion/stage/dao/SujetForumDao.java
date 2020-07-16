package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.SujetForum;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface SujetForumDao extends JpaRepository<SujetForum, Long>, JpaSpecificationExecutor<SujetForum> {
	List<SujetForum> findByDateCreation(Date dateCreation);

	List<SujetForum> findByDateModification(Date dateModification);

	SujetForum findByReference(String reference);

	List<SujetForum> findByUserId(Long id);

	List<SujetForum> findByContentContains(String content);

}
