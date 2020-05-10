package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Encadreur;

@Repository
public interface EncadreurDao extends JpaRepository<Encadreur, Long> {
	
	List<Encadreur> findByProfession(String profession);
	List<Encadreur> findByType(String type);
	List<Encadreur> findByQualite(String qualite);
	Encadreur findByUtilisateurId(Long id);
	Encadreur findByReference(String reference);
	

}
