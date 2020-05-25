package com.gestion.stage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stage.bean.Role;
@Repository
public interface RoleDao extends JpaRepository<Role, Long>{
	Role findByRole(String role);
}
