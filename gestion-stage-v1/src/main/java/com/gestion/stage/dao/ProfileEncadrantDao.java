package com.gestion.stage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.ProfileEncadrant;

@Repository
public interface ProfileEncadrantDao extends JpaRepository<ProfileEncadrant, Long> {
	
List<ProfileEncadrant> findByProfession(String profession);



}
