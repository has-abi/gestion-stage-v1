package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.dao.CoordinateurDao;
import com.gestion.stage.service.facade.CoordinateurService;
import com.gestion.stage.service.facade.FiliereService;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;
@Service
public class CoordinateurServiceImpl implements CoordinateurService{
	
	@Autowired
	private CoordinateurDao coordinateurDao;
	@Autowired
	private UserService userService;
	@Autowired
	private FiliereService filiereService;
	@Autowired
	private RoleService roleService;
	@Override
	public Coordinateur findByReference(String reference) {
		return coordinateurDao.findByReference(reference);
	}

	@Override
	public Coordinateur findByFiliereId(Long id) {
		return coordinateurDao.findByFiliereId(id);
	}

	@Override
	public int save(Coordinateur coordinateur) {
		Coordinateur coord = findByReference(coordinateur.getReference());
		if( coord != null) {
			return -1;
		}else if(coordinateur.getFiliere().getLibelle()==null || coordinateur.getFiliere().getLibelle()=="") {
			return -2;
		}else if(FieldsUtil.utilisateurFields(coordinateur.getUser())<0){
			return -3;
		}else {
			coordinateur.getUser().setReference("u"+DateUtil.getDate().getTime());
			coordinateur.getUser().getRoles().add(roleService.getCoordinateurRole());
			
			if(userService.register(coordinateur.getUser())<0) {
				return -4;
			}
			coordinateur.setUser(userService.findByReference(coordinateur.getUser().getReference()));
			coordinateur.setFiliere(filiereService.findById(coordinateur.getFiliere().getId()));
			coordinateurDao.save(coordinateur);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeByReference(String reference) {
		Coordinateur coord = findByReference(reference);
		if(coord == null) {
			return -1;
		}else {
			coordinateurDao.delete(coord);
			userService.removeById(coord.getUser().getId());
			return 1;
		}
	}

	@Override
	public int update(Coordinateur coordinateur) {
		Coordinateur coord = findByReference(coordinateur.getReference());
		if(coord == null || coordinateur.getFiliere() == null || coordinateur.getUser() == null) {
			return -1;
		}else if(FieldsUtil.utilisateurFields(coordinateur.getUser())<0){
			return -2;
		}else {
			coordinateurDao.save(coord);
			return 1;
		}
	}

	@Override
	public List<Coordinateur> findAll() {
		return coordinateurDao.findAll();
	}

	@Override
	public Coordinateur findByUserId(Long id) {
		return coordinateurDao.findByUserId(id);
	}

	@Override
	public Page<Coordinateur> findAllWithPaginition(int page, int size) {
		return coordinateurDao.findAll(PageRequest.of(page, size));
	}

}
