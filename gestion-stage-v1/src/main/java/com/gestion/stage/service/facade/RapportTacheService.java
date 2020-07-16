package com.gestion.stage.service.facade;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Document;
import com.gestion.stage.bean.RapportTache;
import com.gestion.stage.utils.ResponseMessage;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface RapportTacheService {

	List<RapportTache> findByDateDepot(Date dateDepot);

	List<RapportTache> findByDateModification(Date dateModification);

	List<RapportTache> findByDocument(Document document);

	List<RapportTache> findAll();

	ResponseEntity<ResponseMessage> save(String titre, String description, String StageRef, MultipartFile file);

	ResponseEntity<ResponseMessage> update(String titre, String description, String ref, MultipartFile file);

	int delete(long id, String tacheRef);

	RapportTache findByReference(String reference);
}
