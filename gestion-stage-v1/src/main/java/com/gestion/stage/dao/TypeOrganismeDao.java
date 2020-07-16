package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.TypeOrganisme;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface TypeOrganismeDao extends JpaRepository<TypeOrganisme, Long> {
	TypeOrganisme findByType(String type);
}
