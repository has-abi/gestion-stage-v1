package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;

@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, Long>,JpaSpecificationExecutor<Etudiant>{
	Etudiant findByCin(String cin);
	Etudiant findByCodeAppoge(String codeAppoge);
	List<Etudiant> findByFiliere(Filiere filiere);
	Etudiant findByUtilisateurEmail(String email);
	Etudiant findByUtilisateurId(Long id);
	Page<Etudiant> findByUtilisateurNomContainsOrUtilisateurPrenomContains(String nom,String prenom,Pageable pageable);
	Page<Etudiant> findByNiveau(String niveau,Pageable pageable);
	
}
