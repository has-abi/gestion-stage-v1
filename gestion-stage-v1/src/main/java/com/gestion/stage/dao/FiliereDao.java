package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Filiere;

@Repository
public interface FiliereDao extends JpaRepository<Filiere, Long>{
	List<Filiere> findByDepartementId(Long id);
	
}
