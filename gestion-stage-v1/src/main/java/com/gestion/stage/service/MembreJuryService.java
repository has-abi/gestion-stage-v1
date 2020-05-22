package com.gestion.stage.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.MembreJury;

public interface MembreJuryService {
	MembreJury findByUtilisateurId(Long id);
	MembreJury findByUtilisateurEmail(String email);
	Page<MembreJury> findByProfession(String profession,int page,int size);
	List<MembreJury> findAll();
	MembreJury findByReference(String reference);
	int save(MembreJury membreJury);
	int update(MembreJury membreJury);
	int removeByReference(String reference);
	Page<MembreJury> findAllWithPaginition(int page,int size);
	Page<MembreJury> findByUtilisateurNomContainsOrUtilisateurPrenomContains(String nom,String prenom,int page,int size);
	ResponseEntity<List<MembreJury>> searchForJuries(Specification<MembreJury> spec);
}
