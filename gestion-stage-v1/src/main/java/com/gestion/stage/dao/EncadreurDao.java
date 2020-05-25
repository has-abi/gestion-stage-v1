package com.gestion.stage.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Encadreur;

@Repository
public interface EncadreurDao extends JpaRepository<Encadreur, Long>,JpaSpecificationExecutor<Encadreur>{
	
	Page<Encadreur> findByProfession(String profession,Pageable pageable);
	Page<Encadreur> findByType(String type,Pageable pageable);
	Page<Encadreur> findByQualite(String qualite,Pageable pageable);
	Encadreur findByUserId(Long id);
	Encadreur findByReference(String reference);
	Page<Encadreur> findByUserNomContainsOrUserPrenomContains(String nom,String prenom,Pageable pageable);
	

}
