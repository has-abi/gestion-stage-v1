package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.dao.SujetForumDao;
import com.gestion.stage.service.facade.SujetForumService;

@Service
public class SujetForumServiceImpl implements SujetForumService {
	@Autowired
	private SujetForumDao sujetForumDao;

	@Override
	public int save(SujetForum sujetForum) {

		SujetForum sujetFounded=findByReference(sujetForum.getReference());
		if(sujetFounded!=null) {
			return -1;}else {
				sujetForumDao.save(sujetForum);
				return 2;
				
			}
		
	}

	@Override
	public List<SujetForum> findAll() {
		
		return sujetForumDao.findAll();
	}

	@Override
	public List<SujetForum> findByDateCreation(Date dateCreation) {
	return sujetForumDao.findByDateCreation(dateCreation);
		
	}

	@Override
	public List<SujetForum> findByDateModification(Date dateModification) {
		return sujetForumDao.findByDateModification(dateModification);
	}

	@Override
	public int remove(SujetForum sujetForum) {
	SujetForum sujetFounded=findByReference(sujetForum.getReference());
	if(sujetFounded==null) {
		return -1;
	}else {
		sujetForumDao.delete(sujetForum);
		return 1;
	}
	}
	@Override
	public SujetForum findByReference(String reference) {
		return sujetForumDao.findByReference(reference);
	}

}
