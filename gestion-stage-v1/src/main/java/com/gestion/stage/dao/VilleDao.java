package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Ville;

@Repository
public interface VilleDao extends JpaRepository<Ville, Long>{

}
