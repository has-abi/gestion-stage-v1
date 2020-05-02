package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Paye;

@Repository
public interface PayeDao extends JpaRepository<Paye,Long> {
	List<Paye> findByNom(String nom);
	
	

}
