package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Ville;

@Repository
public interface VilleDao extends JpaRepository<Ville, Long>{
	List<Ville> findByPaysNom(String nom);
	Ville findByPaysNomAndNom(String nomPays,String nom);
}
