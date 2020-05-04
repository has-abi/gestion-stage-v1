package com.gestion.stage.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.MembreJury;

@Repository
public interface MembreJuryDao  extends JpaRepository<MembreJury, Long>{
	
	

}
