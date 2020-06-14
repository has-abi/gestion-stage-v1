package com.gestion.stage.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.MembreJury;

@Repository
public interface MembreJuryDao  extends JpaRepository<MembreJury, Long>,JpaSpecificationExecutor<MembreJury>{
	
	MembreJury findByUserId(Long id);
	MembreJury findByUserEmail(String email);
	Page<MembreJury> findByProfession(String profession,Pageable pageable);
	Page<MembreJury> findByUserNomContainsOrUserPrenomContains(String nom,String prenom,Pageable pageable);
	MembreJury findByReference(String reference);
	@Query(value = "Select membre_jury.* from membre_jury,stage_membre_jury,stage where stage.coordinateur = :id and stage_membre_jury.stage = stage.id and membre_jury.id = stage_membre_jury.membre_jury ",nativeQuery = true)
	Page<MembreJury> findByCoordinateur(@Param("id") Long id,Pageable pageable);
	
	@Query(value = "select distinct membre_jury.* from membre_jury,stage,stage_membre_jury,coordinateur where coordinateur.filiere = :id and  stage.coordinateur = coordinateur.id and stage_membre_jury.stage = stage.id and membre_jury.id = stage_membre_jury.membre_jury",nativeQuery = true)
	List<MembreJury> findByFiliere(@Param("id") Long id);
}
