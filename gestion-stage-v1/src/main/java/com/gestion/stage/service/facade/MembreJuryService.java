package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.MembreJury;

public interface MembreJuryService {
	MembreJury findByUserId(Long id);

	MembreJury findByUserEmail(String email);

	Page<MembreJury> findByProfession(String profession, int page, int size);

	Page<MembreJury> findAll(int page, int size, String sort);

	MembreJury findByReference(String reference);

	int save(MembreJury membreJury);

	int update(MembreJury membreJury);

	int removeByReference(String reference);

	Page<MembreJury> findAllWithPaginition(int page, int size);

	Page<MembreJury> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page, int size);

	ResponseEntity<List<MembreJury>> searchForJuries(Specification<MembreJury> spec);

	Page<MembreJury> findByCoordinateur(Long id, int page, int size, String sort);
	
	int countJuries();
	
	List<MembreJury> findByFiliere(Long id);
}
