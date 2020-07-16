package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Document;
import com.gestion.stage.bean.RapportTache;
import com.gestion.stage.bean.Tache;
import com.gestion.stage.dao.RapportTacheDao;
import com.gestion.stage.dao.TacheDao;
import com.gestion.stage.service.facade.DocumentService;
import com.gestion.stage.service.facade.RapportTacheService;
import com.gestion.stage.service.facade.TacheService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FileUtil;
import com.gestion.stage.utils.ResponseMessage;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class RapportTacheServiceImpl implements RapportTacheService {
	@Autowired
	private RapportTacheDao rapportTacheDao;
	@Autowired
	private TacheService tacheService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private TacheDao tacheDao;

	@Override
	public List<RapportTache> findByDateDepot(Date dateDepot) {
		return rapportTacheDao.findByDateDepot(dateDepot);
	}

	@Override
	public List<RapportTache> findByDateModification(Date dateModification) {
		return rapportTacheDao.findByDateModification(dateModification);
	}

	@Override
	public List<RapportTache> findByDocument(Document document) {
		return null;
	}

	@Override
	public List<RapportTache> findAll() {
		return rapportTacheDao.findAll();
	}

	@Override
	public ResponseEntity<ResponseMessage> save(String titre, String description, String TacheRef, MultipartFile file) {
		System.out.println("we get the service");
		Tache tache = tacheService.findByReference(TacheRef);
		if (tache == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("tache n'existe pas"));
		} else {

			MultipartFile fileToStore = FileUtil.getNewFile(FileUtil.getEditedName(file), file);
			if (documentService.save(titre, fileToStore).getStatusCode() == HttpStatus.EXPECTATION_FAILED) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseMessage("on ne peut pas uploader le fichier correctement!"));
			} else {
				System.out.println("in else");
				RapportTache rapport = new RapportTache();
				rapport.setDateDepot(DateUtil.getDate());
				rapport.setValider(false);
				Date date = new Date();
				String ref = "rapp" + date.getTime();
				rapport.setReference(ref);
				rapport.setDescreption(description);
				rapport.setDocument(documentService.findByReference(fileToStore.getOriginalFilename()));
				rapportTacheDao.save(rapport);
				tache.setRapportTache(findByReference(ref));
				tacheDao.save(tache);

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("rapport uploader avec succée!"));
			}
		}
	}

	@Override
	public ResponseEntity<ResponseMessage> update(String titre, String description, String ref, MultipartFile file) {
		RapportTache foundedRapport = findByReference(ref);

		if (foundedRapport == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseMessage("on ne peut pas uploader le fichier correctement!"));
		} else {
			if (foundedRapport.isValider()) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseMessage("on ne peut pas modifier le rapport car il est dèjà valider!!"));
			}
			MultipartFile fileToStore = FileUtil.getNewFile(FileUtil.getEditedName(file), file);
			if (documentService.update(titre, fileToStore, foundedRapport.getDocument().getReference())
					.getStatusCode() == HttpStatus.EXPECTATION_FAILED) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseMessage("on ne peut pas uploader le fichier correctement!"));
			} else {
				foundedRapport.getDocument().setTitre(titre);
				foundedRapport.setDescreption(description);
				rapportTacheDao.save(foundedRapport);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("rapport modifier avec succée!"));
			}
		}
	}

	@Transactional
	@Override
	public int delete(long id, String tacheRef) {
		Tache t = tacheService.findByReference(tacheRef);
		if (t == null) {
			return -1;
		} else {
			t.setRapportTache(null);
			tacheService.updateTache(t);
			rapportTacheDao.delete(rapportTacheDao.findById(id).get());
			return 1;
		}
	}

	@Override
	public RapportTache findByReference(String reference) {
		return rapportTacheDao.findByReference(reference);
	}

}
