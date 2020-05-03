package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.SujetForum;

public interface SujetForumService {
	List<SujetForum> findByContent(String content);
	List<SujetForum> findByDateCreation(Date dateCreation);
	List<SujetForum> findByDateModification(Date dateModification);
	int save(SujetForum sujetForum);
	List<SujetForum> findAll();

}
