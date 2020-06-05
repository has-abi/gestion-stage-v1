package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Rapport;
import com.gestion.stage.utils.ResponseMessage;

public interface RapportService {
	List<Rapport> findByDateDepot(Date dateDepot);

	List<Rapport> findByDateSoutenance(Date dateSoutenance);

	List<Rapport> findByDescreption(String descreption);

	ResponseEntity<ResponseMessage> save(String titre, String description, String StageRef, MultipartFile file);

	ResponseEntity<ResponseMessage> updateRapport(String titre, String description, String ref, MultipartFile file);

	List<Rapport> findAll();

	Rapport findByReference(String reference);

	int validerRapport(String ref);

	int countRapports();
	
	ResponseEntity<List<Rapport>> searchForRapports(Specification<Rapport> spec);
	
	Page<Rapport> findAllWithPagination(int page,int size,String sort);


}
