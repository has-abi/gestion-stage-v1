package com.gestion.stage.service.facade;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;

public interface EtudiantService {
	Etudiant findByCin(String cin);
	Etudiant findByCodeAppoge(String codeAppoge);
	List<Etudiant> findByFiliere(Filiere filiere);
	int save(Etudiant etudiant);
	List<Etudiant> findAll();
	int Update(Etudiant etudiant);
	int removeByCin(String cin);
	Etudiant findByUserEmail(String email);
	Etudiant findByUserId(Long id);
	Page<Etudiant> findAllWithPaginition(int page,int size);
	Page<Etudiant> findByUserNomContainsOrUserPrenomContains(String nom,String prenom,int page,int size);
	Page<Etudiant> findByNiveau(String niveau,int page,int size);
	ResponseEntity<List<Etudiant>> searchForEtudiants(Specification<Etudiant> spec);
	List<Etudiant> readXsl() throws IOException;
}
