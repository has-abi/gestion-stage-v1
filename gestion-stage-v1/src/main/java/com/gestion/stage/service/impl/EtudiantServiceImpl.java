package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.EtudiantDao;
import com.gestion.stage.service.EtudiantService;
import com.gestion.stage.service.FiliereService;
import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.utils.FieldsUtil;


@Service
public class EtudiantServiceImpl implements EtudiantService{
	@Autowired
	private EtudiantDao etudiantDao;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private FiliereService filiereService;
	@Override
	public Etudiant findByCin(String cin) {
		return etudiantDao.findByCin(cin);
	}

	@Override
	public Etudiant findByCodeAppoge(String codeAppoge) {
		return etudiantDao.findByCodeAppoge(codeAppoge);
	}

	@Override
	public List<Etudiant> findByFiliere(Filiere filiere) {
		return etudiantDao.findByFiliere(filiere);
	}

	@Override
	public int save(Etudiant etudiant) {
		if(FieldsUtil.etudiantFields(etudiant)<0) {
			return -1;
		}else {
			Etudiant foundedEtudByCin = findByCin(etudiant.getCin());
			Etudiant foundeEtudByCode = findByCodeAppoge(etudiant.getCodeAppoge());
			Filiere filiere = filiereService.findById(etudiant.getFiliere().getId());
			if(foundedEtudByCin !=null) {
				return -2;
			}else if(foundeEtudByCode != null) {
				return -3;
			}else if(filiere == null) {
				return -4;
			}else {
				etudiant.getUtilisateur().setRole(1);
				if(utilisateurService.register(etudiant.getUtilisateur())<0) {
					return -5;
				}
				
				etudiant.setUtilisateur(utilisateurService.findByEmail(etudiant.getUtilisateur().getEmail()));
				etudiantDao.save(etudiant);
				return 1;
			}
		}
	}

	@Override
	public List<Etudiant> findAll() {
		return etudiantDao.findAll();
	}

	@Override
	public int Update(Etudiant etudiant) {
		Etudiant etud = etudiantDao.findById(etudiant.getId()).get();
		if(etud == null) {
			return -1;
		}else if(FieldsUtil.etudiantFields(etudiant)<0) {
			return -2;
		}else {
			List<Etudiant> etuds = findAll();
			for(Etudiant e : etuds) {
				if(e.getId()!=etudiant.getId() && (e.getCin().equals(etudiant.getCin()) || e.getCodeAppoge().equals(etudiant.getCodeAppoge()))) {
					return -3;
				}
			}
			etudiantDao.save(etudiant);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeByCin(String cin) {
		Etudiant etud = findByCin(cin);
		if(etud == null) {
			return -1;
		}else {
			Utilisateur u = etud.getUtilisateur();
			etudiantDao.delete(etud);
			utilisateurService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public Etudiant findByUilisateurEmail(String email) {
		return etudiantDao.findByUtilisateurEmail(email);
	}

	@Override
	public Etudiant findByUtilisateurId(Long id) {
		return etudiantDao.findByUtilisateurId(id);
	}

}
