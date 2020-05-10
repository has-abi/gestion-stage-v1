package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Administrateur;
import com.gestion.stage.bean.Etablissement;
import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.AdministrateurDao;
import com.gestion.stage.service.AdministrateurService;
import com.gestion.stage.service.EtablissementService;
import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	@Autowired
	private AdministrateurDao administrateurDao;
	@Autowired
	private EtablissementService etablissementService;
	@Autowired
	private UtilisateurService utilisateurService;
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
		}else if(FieldsUtil.utilisateurFields(administrateur.getUtilisateur())<0){
			return -2;
		}else if(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()) == null){
			return -3;
		}else {
			administrateur.getUtilisateur().setRole(5);
			administrateur.setEtablissement(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()));
			if(utilisateurService.register(administrateur.getUtilisateur())<0) {
				return -4;
			}
			administrateur.setUtilisateur(utilisateurService.findByEmail(administrateur.getUtilisateur().getEmail()));
			administrateurDao.save(administrateur);
			return 1;
		}
	}

	@Override
	public Administrateur findByUtilisateurEmail(String email) {
		return administrateurDao.findByUtilisateurEmail(email);
	}

	@Override
	public int update(Administrateur administrateur) {
		if(findByRef(administrateur.getRef()) == null) {
			return -1;
		}else if(FieldsUtil.utilisateurFields(administrateur.getUtilisateur())<0){
			return -2;
		}else if(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()) == null){
			return -3;
		}else {
			administrateur.setEtablissement(etablissementService.findByLibelle(administrateur.getEtablissement().getLibelle()));
			administrateur.setUtilisateur(utilisateurService.findByEmail(administrateur.getUtilisateur().getEmail()));
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
			Utilisateur u = admin.getUtilisateur();
			administrateurDao.delete(admin);
			utilisateurService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public Administrateur findByUtilisateurId(Long id) {
		return administrateurDao.findByUtilisateurId(id);
	}

	@Override
	public Administrateur findByRef(String ref) {
		return administrateurDao.findByRef(ref);
	}

}
