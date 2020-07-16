package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.SujetForum;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface SujetForumService {
	int save(SujetForum sujetForum);

	List<SujetForum> findAll();

	List<SujetForum> findByDateCreation(Date dateCreation);

	List<SujetForum> findByDateModification(Date dateModification);

	int remove(String reference);
	
	int update(SujetForum sujetForum);

	SujetForum findByReference(String reference);

	List<SujetForum> findByUserId(Long id);
	
	Page<SujetForum> findAllWithPagination(int page,int size,String sort);
	
	 ResponseEntity<List<SujetForum>> searchForSujetForums(Specification<SujetForum> spec);
	 
	 List<SujetForum> findByContentContains(String content);

	int countSujets();
}
