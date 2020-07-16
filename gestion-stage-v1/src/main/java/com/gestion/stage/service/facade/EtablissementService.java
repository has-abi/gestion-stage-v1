package com.gestion.stage.service.facade;

import java.util.List;

import com.gestion.stage.bean.Etablissement;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface EtablissementService {

	int save(Etablissement etablissement);

	int update(Etablissement etablissement);

	List<Etablissement> findAll();
}
