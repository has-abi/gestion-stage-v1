package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Ville;
import com.gestion.stage.utils.VilleStatistics;

public interface VilleService {
	Ville findbyId(Long id);

	List<Ville> findByPaysNom(String nom);

	int save(Ville ville);

	int removeByid(Long id);

	int update(Ville ville);

	List<Ville> findAll();

	Ville findByPaysNomAndNom(String pays, String nom);

	int countVilles();
	
	List<VilleStatistics> numberorganismeByVille(Long id);
	
	List<Ville> villesParFilier(Long idFiliere);
}
