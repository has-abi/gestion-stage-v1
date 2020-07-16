package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Role;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
