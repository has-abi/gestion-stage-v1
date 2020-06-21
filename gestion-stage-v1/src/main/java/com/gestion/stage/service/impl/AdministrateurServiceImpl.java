package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Administrateur;
import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.AdministrateurDao;
import com.gestion.stage.service.facade.AdministrateurService;
import com.gestion.stage.service.facade.EtablissementService;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	@Autowired
	private AdministrateurDao administrateurDao;
	@Autowired
	private EtablissementService etablissementService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Override
	public List<Administrateur> findByProfessionContains(String profession) {
		return administrateurDao.findByProfessionContains(profession);
	}

	@Override
	public List<Administrateur> findByEtablissement(Etablissement etablissement) {
		return administrateurDao.findByEtablissement(etablissement);
	}

	@Override
	public List<Administrateur> findAll() {
		return administrateurDao.findAll();
	}

	@Override
	public int save(Administrateur administrateur) {
		if(findByRef(administrateur.getRef())!=null) {
			return -1;
		}else if(FieldsUtil.utilisateurFields(administrateur.getUser())<0){
			return -2;
		}else if(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()) == null){
			return -3;
		}else {
			administrateur.getUser().setReference("u"+DateUtil.getDate().getTime());
			administrateur.getUser().getRoles().add(roleService.getAdminRole());
			if(userService.register(administrateur.getUser())<0) {
				return -4;
			}
			administrateur.setUser(userService.findByReference(administrateur.getUser().getReference()));
			administrateurDao.save(administrateur);
			return 1;
		}
	}

	@Override
	public Administrateur findByUserEmail(String email) {
		return administrateurDao.findByUserUsername(email);
	}

	@Override
	public int update(Administrateur administrateur) {
		if(findByRef(administrateur.getRef()) == null) {
			return -1;
		}else if(FieldsUtil.utilisateurFields(administrateur.getUser())<0){
			return -2;
		}else if(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()) == null){
			return -3;
		}else {
			administrateur.setEtablissement(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()));
			administrateur.setUser(userService.findByReference(administrateur.getUser().getReference()));
			administrateurDao.save(administrateur);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeByRef(String ref) {
		Administrateur admin = findByRef(ref);
		if(admin == null) {
			return -1;
		}else {
			User u = admin.getUser();
			administrateurDao.delete(admin);
			userService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public Administrateur findByUserId(Long id) {
		return administrateurDao.findByUserId(id);
	}

	@Override
	public Administrateur findByRef(String ref) {
		return administrateurDao.findByRef(ref);
	}

}
