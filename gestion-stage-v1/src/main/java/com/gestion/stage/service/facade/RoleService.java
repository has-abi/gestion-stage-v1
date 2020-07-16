package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Role;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface RoleService {

	Role findByRole(String role);

	Role getAdminRole();

	Role getEtudiantRole();

	Role getCoordinateurRole();

	Role getEncadreurRole();

	Role getJuryRole();

	List<Role> findAll();

}
