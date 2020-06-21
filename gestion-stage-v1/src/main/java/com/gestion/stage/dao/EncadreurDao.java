package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	@Query(value = "Select DISTINCT encadreur.* from encadreur,stage_encadreur,stage where stage.coordinateur = :id and stage_encadreur.stage = stage.id and encadreur.id = stage_encadreur.encadreur ",nativeQuery = true)
	Page<Encadreur> findByCoordinateur(@Param("id")Long id,Pageable pageable);
	Encadreur findByUserUsername(String username);
	
	@Query(value = "select DISTINCT encadreur.* from encadreur,stage,stage_encadreur,coordinateur where coordinateur.filiere = :id and  stage.coordinateur = coordinateur.id and stage_encadreur.stage = stage.id and encadreur.id = stage_encadreur.encadreur",nativeQuery = true)
	List<Encadreur> findByFiliere(@Param("id") Long id);

}
