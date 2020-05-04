package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.service.EtudiantService;


@Service
public class EtudiantServiceImpl implements EtudiantService{

	@Override
	public Etudiant findByCin(String cin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Etudiant findByCodeAppoge(String codeAppoge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> findByFiliere(Filiere filiere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Etudiant etudiant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Etudiant> findALL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Update(Etudiant etudiant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeByCin(String cin) {
		// TODO Auto-generated method stub
		return 0;
	}

}
