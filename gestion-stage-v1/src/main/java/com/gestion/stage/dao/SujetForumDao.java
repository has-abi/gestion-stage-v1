package com.gestion.stage.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.SujetForum;

@Repository
public interface SujetForumDao extends JpaRepository<SujetForum, Long>{
	List<SujetForum> findByContent(String content);
	List<SujetForum> findByDateCreation(Date dateCreation);
	List<SujetForum> findByDateModification(Date dateModification);
	
	

}
