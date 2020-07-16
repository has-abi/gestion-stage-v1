package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface EtudiantService {
	Etudiant findByCin(String cin);

	Etudiant findByCodeAppoge(String codeAppoge);

	List<Etudiant> findByFiliere(Filiere filiere);

	int save(Etudiant etudiant);

	Page<Etudiant> findAll(int page, int size, String sort);

	int Update(Etudiant etudiant);

	int removeByCin(String cin);

	Etudiant findByUserEmail(String email);

	Etudiant findByUserId(Long id);

	Page<Etudiant> findAllWithPaginition(int page, int size);

	Page<Etudiant> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page, int size);

	Page<Etudiant> findByNiveau(String niveau, int page, int size);

	ResponseEntity<List<Etudiant>> searchForEtudiants(Specification<Etudiant> spec);

	Page<Etudiant> findByCoordinateur(long id, int page, int size, String sort);
	
	List<Etudiant> findByEncadreurid(Long id);
	
	List<Etudiant> findByJuryId(Long id);
	
	int countEtdudiants();
	
	List<Etudiant> findByFiliere(Long id);
	
	int  validateEtudiant(String cne,String codeAppoge);
	
	

}
