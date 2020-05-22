package com.gestion.stage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gestion.stage.bean.Coordinateur;

public interface CoordinateurService {
	Coordinateur findByReference(String reference);
	Coordinateur findByFiliereId(Long id);
	int save(Coordinateur coordinateur);
	int removeByReference(String reference);
	int update(Coordinateur coordinateur);
	List<Coordinateur> findAll();
	Coordinateur findByUtilisateurId(Long id);
	Page<Coordinateur> findAllWithPaginition(int page,int size);
}
