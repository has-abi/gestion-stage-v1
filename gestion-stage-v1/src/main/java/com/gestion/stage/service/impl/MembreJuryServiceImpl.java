package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.MembreJury;
import com.gestion.stage.bean.Utilisateur;
import com.gestion.stage.dao.MembreJuryDao;
import com.gestion.stage.service.MembreJuryService;
import com.gestion.stage.service.UtilisateurService;
import com.gestion.stage.util.FieldsUtil;
@Service
public class MembreJuryServiceImpl implements MembreJuryService{
	@Autowired
	private MembreJuryDao membreJuryDao;
	@Autowired
	private UtilisateurService utilisateurService;
	@Override
	public MembreJury findByUtilisateurId(Long id) {
		return membreJuryDao.findByUtilisateurId(id);
	}

	@Override
	public List<MembreJury> findByProfession(String profession) {
		return membreJuryDao.findByProfession(profession);
	}

	@Override
	public List<MembreJury> findAll() {
		return membreJuryDao.findAll();
	}

	@Override
	public int save(MembreJury membreJury) {
		if(FieldsUtil.juryFields(membreJury)<0) {
			return -1;
		}else {
			MembreJury foundedJury = findByReference(membreJury.getReference());
			if(foundedJury != null) {
				return -2;
			}else {
				membreJury.getUtilisateur().setRole(3);
				if (utilisateurService.register(membreJury.getUtilisateur())<0) {
					return -3;
				};
				membreJury.setUtilisateur(utilisateurService.findByEmail(membreJury.getUtilisateur().getEmail()));
				membreJuryDao.save(membreJury);
				return 1;
			}
		}
	}

	@Override
	public int update(MembreJury membreJury) {
		MembreJury foundedjury = membreJuryDao.findById(membreJury.getId()).get();
		if(foundedjury == null) {
			return -1;
		}else if(FieldsUtil.juryFields(membreJury)<0) {
			return -2;
		}else {
			List<MembreJury> memJuries = findAll();
			for(MembreJury m : memJuries) {
				if(m.getId() == membreJury.getId() && m.getReference().equals(membreJury.getReference())) {
					return -3;
				}
			}
			membreJuryDao.save(membreJury);
			return 1;
		}
	}
	@Transactional
	@Override
	public int removeById(Long id) {
		MembreJury foundedjury = membreJuryDao.findById(id).get();
		if(foundedjury == null) {
			return -1;
		}else {
			Utilisateur u = foundedjury.getUtilisateur();
			membreJuryDao.delete(foundedjury);
			utilisateurService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public MembreJury findByUtilisateurEmail(String email) {
		return membreJuryDao.findByUtilisateurEmail(email);
	}

	@Override
	public MembreJury findByReference(String reference) {
		return membreJuryDao.findByReference(reference);
	}

}
