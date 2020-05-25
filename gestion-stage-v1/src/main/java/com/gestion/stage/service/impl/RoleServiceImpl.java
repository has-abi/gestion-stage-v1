package com.gestion.stage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Role;
import com.gestion.stage.dao.RoleDao;
import com.gestion.stage.service.facade.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Override
	public Role findByRole(String role) {
		return roleDao.findByRole(role);
	}

	@Override
	public Role getAdminRole() {
		return findByRole("ADMIN_ROLE");
	}

	@Override
	public Role getEtudiantRole() {
		return findByRole("ETUDIANT_ROLE");
	}

	@Override
	public Role getCoordinateurRole() {
		return findByRole("COORDINATEUR_ROLE");
	}

	@Override
	public Role getEncadreurRole() {
		return findByRole("ENCADREUR_ROLE");
	}

	@Override
	public Role getJuryRole() {
		return findByRole("JURY_ROLE");
	}

}
