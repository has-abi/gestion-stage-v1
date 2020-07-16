package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Rapport;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.dao.RapportDao;
import com.gestion.stage.dao.StageDao;
import com.gestion.stage.service.facade.DocumentService;
import com.gestion.stage.service.facade.RapportService;
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FileUtil;
import com.gestion.stage.utils.ResponseMessage;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class RapportServiceImpl implements RapportService {
	@Autowired
	private RapportDao rapportDao;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private StageService stageService;
	@Autowired
	private StageDao stageDao;

	@Override
	public ResponseEntity<ResponseMessage> save(String titre, String description, String StageRef, MultipartFile file) {
		Stage foundedStage = stageService.findByReference(StageRef);
		if (foundedStage == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("stage n'existe pas"));
		} else {
			if (!foundedStage.isStatu()) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(
						"impossible d'enregister ce rapport car le stage n'est pas encore acitiver!!"));
			}
			MultipartFile fileToStore = FileUtil.getNewFile(FileUtil.getEditedName(file), file);
			if (documentService.save(titre, fileToStore).getStatusCode() == HttpStatus.EXPECTATION_FAILED) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseMessage("on ne peut pas uploader le fichier correctement!"));
			} else {
				Rapport rapport = new Rapport();
				rapport.setDateDepot(DateUtil.getDate());
				rapport.setDescreption(description);
				rapport.setValider(false);
				Date date = new Date();
				String ref = "rapp" + date.getTime();
				rapport.setReference(ref);
				rapport.setDocument(documentService.findByReference(fileToStore.getOriginalFilename()));
				rapportDao.save(rapport);
				foundedStage.setRapport(findByReference(ref));
				stageDao.save(foundedStage);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("rapport uploader avec succée!"));
			}
		}
	}

	@Override
	public List<Rapport> findAll() {
		return rapportDao.findAll();
	}

	@Override
	public Rapport findByReference(String reference) {
		return rapportDao.findByReference(reference);
	}

	@Override
	public ResponseEntity<ResponseMessage> updateRapport(String titre, String description, String ref,
			MultipartFile file) {
		Rapport foundedRapport = findByReference(ref);

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
				foundedRapport.setDescreption(description);
				rapportDao.save(foundedRapport);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("rapport modifier avec succée!"));
			}
		}
	}

	@Override
	public int validerRapport(String ref) {
		Rapport foundedRapport = findByReference(ref);
		if (foundedRapport == null) {
			return -1;
		} else {
			foundedRapport.setValider(true);
			foundedRapport.setDateValidation(DateUtil.getDate());
			rapportDao.save(foundedRapport);
			return 1;
		}
	}

	@Override
	public int countRapports() {
		return (int) rapportDao.count();
	}

	@Override
	public ResponseEntity<List<Rapport>> searchForRapports(Specification<Rapport> spec) {
		return new ResponseEntity<>(rapportDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	@Override
	public Page<Rapport> findAllWithPagination(int page, int size, String sort) {
		if (sort.equals("asc")) {
			return rapportDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return rapportDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public int delete(Long id, String stageRef) {
		Stage s = stageService.findByReference(stageRef);
		if (s == null) {
			return -1;
		} else {
			s.setRapport(null);
			stageService.update(s);
			rapportDao.delete(rapportDao.findById(id).get());
			return 1;
		}
	}

}
