package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.TypeServiceOrganisme;

@Repository
public interface TypeServiceOrganismeDao extends JpaRepository<TypeServiceOrganisme, Long> {
	TypeServiceOrganisme findByType(String type);
}
