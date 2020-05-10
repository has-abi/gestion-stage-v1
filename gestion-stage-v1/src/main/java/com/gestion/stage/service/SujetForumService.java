package com.gestion.stage.service;

import java.util.Date;
import java.util.List;

import com.gestion.stage.bean.SujetForum;

public interface SujetForumService {
	int save(SujetForum sujetForum);
	List<SujetForum> findAll();
	List<SujetForum> findByDateCreation(Date dateCreation);
	List<SujetForum> findByDateModification(Date dateModification);
	int remove(SujetForum sujetForum);
	SujetForum  findByReference(String reference);
}
