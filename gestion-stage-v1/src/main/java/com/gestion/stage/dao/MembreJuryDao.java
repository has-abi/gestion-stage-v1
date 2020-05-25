package com.gestion.stage.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.MembreJury;

@Repository
public interface MembreJuryDao  extends JpaRepository<MembreJury, Long>,JpaSpecificationExecutor<MembreJury>{
	
	MembreJury findByUserId(Long id);
	MembreJury findByUserEmail(String email);
	Page<MembreJury> findByProfession(String profession,Pageable pageable);
	Page<MembreJury> findByUserNomContainsOrUserPrenomContains(String nom,String prenom,Pageable pageable);
	MembreJury findByReference(String reference);
}
