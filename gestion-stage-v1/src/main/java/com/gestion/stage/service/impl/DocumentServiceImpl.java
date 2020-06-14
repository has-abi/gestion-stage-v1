package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Document;
import com.gestion.stage.dao.DocumentDao;
import com.gestion.stage.service.facade.DocumentService;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.utils.FileUtil;
import com.gestion.stage.utils.ResponseMessage;
import com.google.common.net.HttpHeaders;

@Service
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private FileStorageService fileStorageService;

	@Override
	public Document findByTitre(String titre) {
		return documentDao.findByTitre(titre);
	}

	@Override
	public Document findByReference(String reference) {
		return documentDao.findByReference(reference);
	}

	@Override
	public int deleteByReference(String reference) {
		Document documentFounded = findByReference(reference);
		if (documentFounded == null) {
			return -1;
		} else {
			documentDao.delete(documentFounded);
			return 1;
		}
	}

	@Override
	public ResponseEntity<ResponseMessage> save(String titre,MultipartFile file) {
		String message = "";
		Document document = new Document();
		document.setReference(file.getOriginalFilename());
		document.setTitre(titre);
		document.setType(FileUtil.getExt(file));
		Document documentFounded=findByReference(document.getReference());
		if(documentFounded!=null) {
			message += "Nom du fichier est dèja exister!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}else {
			try {
				message = "le ficher : " + file.getOriginalFilename() + " enregister avec succée!";
				fileStorageService.save(file);
				documentDao.save(document);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}catch(Exception e) {
				message = "on ne peut pas uploader le fichier: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
			
		}

	}

	@Override
	public List<Document> findAll() {
		return documentDao.findAll();
	}

	@Override
	public ResponseEntity<Resource> loadFile(String filename) {
		 Resource file = fileStorageService.loadDocs(filename);
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@Override
	public ResponseEntity<ResponseMessage> update(String titre, MultipartFile file,String ref) {
		String message = "";
		Document document = findByReference(ref);
		if(document == null) {
			message += "document introuvable!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}else  {
			if(file != null){
			document.setReference(file.getOriginalFilename());
			document.setType(FileUtil.getExt(file));
				}
			
			document.setTitre(titre);
			
			Document documentFounded=findByReference(document.getReference());
			if(documentFounded!=null) {
				message += "Nom du fichier est dèja exister!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}else {
				try {
					if(file !=null){
					fileStorageService.save(file);
					}
					message = "le ficher : " + file.getOriginalFilename() + " modifier avec succée!";
					documentDao.save(document);
					return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
				}catch(Exception e) {
					message = "on ne peut pas uploader le fichier: " + file.getOriginalFilename() + "!";
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
				}
				
			}
		}
		
	}

}
