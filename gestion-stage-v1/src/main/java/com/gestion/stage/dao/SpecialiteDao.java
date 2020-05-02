package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Departement;
import com.gestion.stage.bean.Specialite;

@Repository
public interface SpecialiteDao extends JpaRepository<Specialite, Long>{
	List<Specialite> findByLibelle(String libelle);
	List<Specialite> findByDepartement(Departement departement);
	
	
	

}
