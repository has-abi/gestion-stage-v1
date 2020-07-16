package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gestion.stage.bean.Coordinateur;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface CoordinateurService {
	Coordinateur findByReference(String reference);

	Coordinateur findByFiliereId(Long id);

	int save(Coordinateur coordinateur);

	int removeByReference(String reference);

	int update(Coordinateur coordinateur);

	List<Coordinateur> findAll();

	Coordinateur findByUserId(Long id);

	Page<Coordinateur> findAllWithPaginition(int page, int size);
}
