package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gestion.stage.bean.Stage;

public interface StageService {
	Page<Stage> findByDateDebut(String dateDebut, int page, int size);

	Page<Stage> findByDateFin(String dateFin, int page, int size);

	List<Stage> findByDateFinBetween(String date1, String date2);

	Page<Stage> findBySujetContains(String sujet, int page, int size);

	Page<Stage> findByOrganismeAccueilRaisonSocial(String raisonSocial, int page, int size);

	int save(Stage stage);

	Stage findByid(Long id);

	int update(Stage stage);

	int removeByReference(String reference);

	List<Stage> findAll();

	Stage findByReference(String reference);

	Page<Stage> findAllWithPaginition(int page, int size);

	ResponseEntity<List<Stage>> searchForStages(Specification<Stage> spec);

	int activerStage(String ref);

	Page<Stage> findByCoordinateurReference(String reference, int page, int size, String sort);

	Page<Stage> findByEtudiant(Long id, int page, int size);

	Page<Stage> findByEncadreur(Long id, int page, int size);

	Page<Stage> findByJury(Long id, int page, int size);

	Long countByCoordinateurReference(String reference);

	Long countByEtudiant(Long id);

	Long countByEncadreur(Long id);

	Long countByJury(Long id);

}
