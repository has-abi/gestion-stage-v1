package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Rapport;
import com.gestion.stage.utils.ResponseMessage;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface RapportService {

	ResponseEntity<ResponseMessage> save(String titre, String description, String StageRef, MultipartFile file);

	ResponseEntity<ResponseMessage> updateRapport(String titre, String description, String ref, MultipartFile file);

	List<Rapport> findAll();

	Rapport findByReference(String reference);

	int validerRapport(String ref);

	int countRapports();
	
	ResponseEntity<List<Rapport>> searchForRapports(Specification<Rapport> spec);
	
	Page<Rapport> findAllWithPagination(int page,int size,String sort);
	
	int delete(Long id,String stageRef);


}
