package com.gestion.stage.service.facade;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Document;
import com.gestion.stage.utils.ResponseMessage;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface DocumentService {
	Document findByTitre(String titre);

	Document findByReference(String reference);

	int deleteByReference(String reference);

	ResponseEntity<ResponseMessage> save(String titre, MultipartFile file);

	ResponseEntity<ResponseMessage> update(String titre, MultipartFile file, String ref);

	List<Document> findAll();

	ResponseEntity<Resource> loadFile(String filename);
}
