package com.gestion.stage.service.impl;

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

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.bean.StageMembreJury;
import com.gestion.stage.dao.StageDao;
import com.gestion.stage.service.facade.CoordinateurService;
import com.gestion.stage.service.facade.EncadreurService;
import com.gestion.stage.service.facade.EtudiantService;
import com.gestion.stage.service.facade.MembreJuryService;
import com.gestion.stage.service.facade.OrganismeAccueilService;
import com.gestion.stage.service.facade.StageEncadrantService;
import com.gestion.stage.service.facade.StageEtudiantService;
import com.gestion.stage.service.facade.StageMembreJuryService;
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class StageServiceImpl implements StageService {
	@Autowired
	private StageDao stageDao;
	@Autowired
	private OrganismeAccueilService organismeAccueilService;
	@Autowired
	private StageEncadrantService stageEncadrantService;
	@Autowired
	private StageEtudiantService stageEtudiantService;
	@Autowired
	private StageMembreJuryService stageMembreJuryService;
	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private EncadreurService encadreurService;
	@Autowired
	private MembreJuryService membreJuryService;
	@Autowired
	private CoordinateurService coordinateurService;

	@Override
	public Page<Stage> findByDateDebut(String dateDebut, int page, int size) {
		System.out.println(dateDebut);
		return stageDao.findByDateDebut(dateDebut, PageRequest.of(page, size));
	}

	@Override
	public Page<Stage> findByDateFin(String dateFin, int page, int size) {
		return stageDao.findByDateFin(dateFin, PageRequest.of(page, size));
	}

	@Override
	public List<Stage> findByDateFinBetween(String date1, String date2) {
		return stageDao.findByDateFinBetween(date1, date2);
	}

	@Override
	public Page<Stage> findBySujetContains(String sujet, int page, int size) {
		return stageDao.findBySujetContains(sujet, PageRequest.of(page, size));
	}

	@Override
	public Page<Stage> findByOrganismeAccueilRaisonSocial(String raisonSocial, int page, int size) {
		return stageDao.findByOrganismeAccueilRaisonSociale(raisonSocial, PageRequest.of(page, size));
	}

	@Override
	public Stage findByid(Long id) {
		return stageDao.findById(id).get();
	}

	@Override
	public int save(Stage stage) {
		Coordinateur c = coordinateurService.findByReference(stage.getCoordinateur().getReference());
		if (FieldsUtil.StageFields(stage) < 0) {
			return -1;
		} else if (findByReference(stage.getReference()) != null) {
			return -2;
		} else if (DateUtil.compareDates(stage.getDateDebut(), stage.getDateFin()) <= 0) {
			return -3;
		} else if (c == null) {
			return -4;
		} else {

			if (stage.getOrganismeAccueil() != null) {
				organismeAccueilService.save(stage.getOrganismeAccueil());
				OrganismeAccueil oa = organismeAccueilService
						.findByRaisonSocial(stage.getOrganismeAccueil().getRaisonSociale());
				stage.setOrganismeAccueil(oa);
			}
			stage.setStatu(false);
			stage.setCoordinateur(c);
			stage.setDateCreation(DateUtil.getDate());
			stageDao.save(stage);
			if (stage.getStageEtudiants().size() > 0) {
				for (StageEtudiant se : stage.getStageEtudiants()) {
					if (etudiantService.findByCin(se.getEtudiant().getCin()) == null) {
						etudiantService.save(se.getEtudiant());
					}
					se.setStage(findByReference(stage.getReference()));
					se.setEtudiant(etudiantService.findByCin(se.getEtudiant().getCin()));
					se.setDateAffectation(DateUtil.getDate());
					stageEtudiantService.save(se);
				}
			}
			if (stage.getStageEncadreurs().size() > 0) {
				for (StageEncadreur se : stage.getStageEncadreurs()) {
					if (encadreurService.findByReference(se.getEncadreur().getReference()) == null) {
						encadreurService.save(se.getEncadreur());
					}
					se.setDateAffectation(DateUtil.getDate());
					se.setStage(findByReference(stage.getReference()));
					se.setEncadreur(encadreurService.findByReference(se.getEncadreur().getReference()));
					stageEncadrantService.save(se);
				}
			}

			if (stage.getStageMembreJuries().size() > 0) {
				for (StageMembreJury sm : stage.getStageMembreJuries()) {
					if (membreJuryService.findByReference(sm.getMembreJury().getReference()) == null) {
						membreJuryService.save(sm.getMembreJury());
					}
					sm.setDateAffectation(DateUtil.getDate());
					sm.setStage(findByReference(stage.getReference()));
					sm.setMembreJury(membreJuryService.findByReference(sm.getMembreJury().getReference()));
					stageMembreJuryService.save(sm);
				}
			}

			return 1;
		}
	}

	@Override
	public int update(Stage stage) {
		System.out.println(stage);
		if (findByReference(stage.getReference()) == null) {
			return -1;
		} else if (DateUtil.compareDates(DateUtil.getDate(), stage.getDateFin()) < 0) {
			return 2;
		} else {
			if (FieldsUtil.StageFields(stage) < 0) {
				return -2;
			} else {
				if (stage.getOrganismeAccueil() != null) {
					System.out.println(stage.getOrganismeAccueil());
					organismeAccueilService.save(stage.getOrganismeAccueil());
					OrganismeAccueil oa = organismeAccueilService
							.findByRaisonSocial(stage.getOrganismeAccueil().getRaisonSociale());
					stage.setOrganismeAccueil(oa);
				}

				stageDao.save(stage);
				if (stage.getStageEtudiants().size() > 0) {
					for (StageEtudiant se : stage.getStageEtudiants()) {
						if (etudiantService.findByCin(se.getEtudiant().getCin()) == null) {
							etudiantService.save(se.getEtudiant());
						}
						if (stageEtudiantService.findByStageReferenceAndEtudiantCin(stage.getReference(),
								se.getEtudiant().getCin()) == null) {
							se.setStage(findByReference(stage.getReference()));
							se.setEtudiant(etudiantService.findByCin(se.getEtudiant().getCin()));
							se.setDateAffectation(DateUtil.getDate());
							stageEtudiantService.save(se);
						}
					}
				}
				if (stage.getStageEncadreurs().size() > 0) {
					for (StageEncadreur se : stage.getStageEncadreurs()) {
						if (encadreurService.findByUserEmail(se.getEncadreur().getUser().getEmail()) == null) {
							encadreurService.save(se.getEncadreur());
						}
						if (stageEncadrantService.findByStageReferenceAndEncadreurReference(stage.getReference(),
								se.getEncadreur().getReference()) == null) {
							se.setDateAffectation(DateUtil.getDate());
							se.setStage(findByReference(stage.getReference()));
							se.setEncadreur(encadreurService.findByReference(se.getEncadreur().getReference()));
							stageEncadrantService.save(se);
						}
					}
				}

				if (stage.getStageMembreJuries().size() > 0) {
					for (StageMembreJury sm : stage.getStageMembreJuries()) {
						if (membreJuryService.findByReference(sm.getMembreJury().getReference()) == null) {
							membreJuryService.save(sm.getMembreJury());
						}
						if (stageMembreJuryService.findByMembreJuryReferenceAndStageReference(
								sm.getMembreJury().getReference(), stage.getReference()) == null) {
							sm.setDateAffectation(DateUtil.getDate());
							sm.setStage(findByReference(stage.getReference()));
							sm.setMembreJury(membreJuryService.findByReference(sm.getMembreJury().getReference()));
							stageMembreJuryService.save(sm);
						}
					}
				}
				return 1;
			}
		}
	}

	@Transactional
	@Override
	public int removeByReference(String reference) {
		Stage s = findByReference(reference);
		if (s != null) {
			s.getStageEncadreurs().forEach(se -> stageEncadrantService.removeById(se.getId()));
			s.getStageEtudiants().forEach(se -> stageEtudiantService.removeById(se.getId()));
			s.getStageMembreJuries().forEach(sm -> stageMembreJuryService.removeByid(sm.getId()));
			stageDao.delete(s);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<Stage> findAll() {
		return stageDao.findAll();
	}

	@Override
	public Stage findByReference(String reference) {
		return stageDao.findByReference(reference);
	}

	@Override
	public Page<Stage> findAllWithPaginition(int page, int size) {
		return stageDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public ResponseEntity<List<Stage>> searchForStages(Specification<Stage> spec) {
		return new ResponseEntity<>(stageDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	@Override
	public int activerStage(String ref) {
		Stage foundedStage = findByReference(ref);
		if (foundedStage == null) {
			return -1;
		} else if (foundedStage.getOrganismeAccueil() == null) {
			return -2;
		} else if (foundedStage.getSujet() == null || foundedStage.getSujet() == "") {
			return -3;
		} else if (foundedStage.getStageEtudiants().size() == 0) {
			return -4;
		} else if (foundedStage.getStageEncadreurs().size() == 0) {
			return -5;
		} else {
			foundedStage.setStatu(true);
			stageDao.save(foundedStage);
			return 1;
		}
	}

	@Override
	public Page<Stage> findByCoordinateurReference(String reference, int page, int size, String sort) {
		if (sort.equals("asc")) {
			return stageDao.findByCoordinateurReference(reference,
					PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return stageDao.findByCoordinateurReference(reference,
					PageRequest.of(page, size, Sort.by(Direction.DESC, "id")));
		} else if (sort.equals("sujet")) {
			return stageDao.findByCoordinateurReference(reference,
					PageRequest.of(page, size, Sort.by(Direction.ASC, "sujet")));
		} else if (sort.equals("dateDebut")) {
			return stageDao.findByCoordinateurReference(reference,
					PageRequest.of(page, size, Sort.by(Direction.ASC, "dateDebut")));
		} else if (sort.equals("dateFin")) {
			return stageDao.findByCoordinateurReference(reference,
					PageRequest.of(page, size, Sort.by(Direction.ASC, "DateFin")));
		} else {
			return null;
		}

	}

	@Override
	public Page<Stage> findByEtudiant(Long id, int page, int size) {
		return this.stageDao.findByEtudiant(id, PageRequest.of(page, size));
	}

	@Override
	public Page<Stage> findByEncadreur(Long id, int page, int size) {
		return stageDao.findByEncadreur(id, PageRequest.of(page, size));
	}

	@Override
	public Page<Stage> findByJury(Long id, int page, int size) {
		return stageDao.findByJury(id, PageRequest.of(page, size));
	}

	@Override
	public Long countByCoordinateurReference(String reference) {
		return stageDao.countByCoordinateurReference(reference);
	}

	@Override
	public Long countByEtudiant(Long id) {
		return stageDao.countByEtudiant(id);
	}

	@Override
	public Long countByEncadreur(Long id) {
		return stageDao.countByEncadreur(id);
	}

	@Override
	public Long countByJury(Long id) {
		return stageDao.countByJury(id);
	}

	@Override
	public int countStages() {
		return (int) stageDao.count();
	}

}
