package com.gestion.stage.service.facade;

import com.gestion.stage.bean.Role;

public interface RoleService {
	
	Role findByRole(String role);
	Role getAdminRole();
	Role getEtudiantRole();
	Role getCoordinateurRole();
	Role getEncadreurRole();
	Role getJuryRole();
	
}
