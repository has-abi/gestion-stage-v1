package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.TypeServiceOrganisme;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface TypeServiceOrganismeDao extends JpaRepository<TypeServiceOrganisme, Long> {
	TypeServiceOrganisme findByType(String type);
}
