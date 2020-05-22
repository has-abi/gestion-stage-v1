package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.dao.CoordinateurDao;
import com.gestion.stage.service.CoordinateurService;
import com.gestion.stage.service.FiliereService;
import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.utils.FieldsUtil;
@Service
public class CoordinateurServiceImpl implements CoordinateurService{
	
	@Autowired
	private CoordinateurDao coordinateurDao;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private FiliereService filiereService;
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
		}else if(FieldsUtil.utilisateurFields(coordinateur.getUtilisateur())<0){
			return -3;
		}else {
			coordinateur.getUtilisateur().setRole(4);
			utilisateurService.register(coordinateur.getUtilisateur());
			coordinateur.setUtilisateur(utilisateurService.findByEmail(coordinateur.getUtilisateur().getEmail()));
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
			utilisateurService.removeById(coord.getUtilisateur().getId());
			return 1;
		}
	}

	@Override
	public int update(Coordinateur coordinateur) {
		Coordinateur coord = findByReference(coordinateur.getReference());
		if(coord == null || coordinateur.getFiliere() == null || coordinateur.getUtilisateur() == null) {
			return -1;
		}else if(FieldsUtil.utilisateurFields(coordinateur.getUtilisateur())<0){
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
	public Coordinateur findByUtilisateurId(Long id) {
		return coordinateurDao.findByUtilisateurId(id);
	}

	@Override
	public Page<Coordinateur> findAllWithPaginition(int page, int size) {
		return coordinateurDao.findAll(PageRequest.of(page, size));
	}

}
