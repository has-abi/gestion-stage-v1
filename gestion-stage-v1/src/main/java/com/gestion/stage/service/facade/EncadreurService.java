package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.Encadreur;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface EncadreurService {

	Page<Encadreur> findByType(String type, int page, int size);

	Encadreur findByUserId(Long id);

	Encadreur findById(Long id);

	Page<Encadreur> findAll(int page, int size, String sort);

	int save(Encadreur encadreur);

	int update(Encadreur encadreur);

	int removeByReference(String reference);

	Encadreur findByReference(String reference);

	Page<Encadreur> findAllWithPaginition(int page, int size);

	Page<Encadreur> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page, int size);

	ResponseEntity<List<Encadreur>> searchForEncadreurs(Specification<Encadreur> spec);

	Page<Encadreur> findByCoordinateur(Long id, int page, int size, String sort);

	Encadreur findByUserEmail(String email);
	
	 int countEncadreurs();
	 
	 List<Encadreur> findByFiliere(Long id);
	 
	 Encadreur fetchEncadreur(String username);
}
