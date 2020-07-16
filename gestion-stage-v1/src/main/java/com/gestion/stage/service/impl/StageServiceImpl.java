package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.bean.StageMembreJury;
import com.gestion.stage.dao.StageDao;
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

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
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

	@Override
	public Page<Stage> findByDateDebut(String dateDebut, int page, int size) {
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

	@Transactional
	@Override
	public int save(Stage stage) {
		if (FieldsUtil.StageFields(stage) < 0) {
			return -1;
		} else if (findByReference(stage.getReference()) != null) {
			return -2;
		} else if (DateUtil.compareDates(stage.getDateDebut(), stage.getDateFin()) <= 0) {
			return -3;
		} else {
			if (stage.getOrganismeAccueil() != null) {
				organismeAccueilService.save(stage.getOrganismeAccueil());
				OrganismeAccueil oa = organismeAccueilService
						.findByRaisonSocial(stage.getOrganismeAccueil().getRaisonSociale());
				stage.setOrganismeAccueil(oa);
			}
			stage.setStatu(false);
			stage.setDateCreation(DateUtil.getDate());
			stageDao.save(stage);

			if (stage.getStageEtudiants() != null) {
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

			if (stage.getStageEncadreurs() != null) {
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

			if (stage.getStageMembreJuries() != null) {
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

	@Transactional
	@Override
	public int update(Stage stage) {
		if (findByReference(stage.getReference()) == null) {
			return -1;
		} else {
			if (FieldsUtil.StageFields(stage) < 0) {
				return -2;
			} else {
				if (stage.getOrganismeAccueil() != null) {
					organismeAccueilService.save(stage.getOrganismeAccueil());
					OrganismeAccueil oa = organismeAccueilService
							.findByRaisonSocial(stage.getOrganismeAccueil().getRaisonSociale());
					stage.setOrganismeAccueil(oa);
				}

				stageDao.save(stage);
				if (stage.getStageEtudiants().size() > 0) {
					for (StageEtudiant se : stage.getStageEtudiants()) {
						if (stageEtudiantService.findByStageReferenceAndEtudiantCin(stage.getReference(),
								se.getEtudiant().getCin()) == null) {
							if (etudiantService.findByCin(se.getEtudiant().getCin()) == null) {
								etudiantService.save(se.getEtudiant());
							}
							se.setStage(findByReference(stage.getReference()));
							se.setEtudiant(etudiantService.findByCin(se.getEtudiant().getCin()));
							se.setDateAffectation(DateUtil.getDate());
							stageEtudiantService.save(se);
						} else {

							etudiantService.Update(se.getEtudiant());
						}
					}
				}
				if (stage.getStageEncadreurs().size() > 0) {
					for (StageEncadreur se : stage.getStageEncadreurs()) {
						if (stageEncadrantService.findByStageReferenceAndEncadreurReference(stage.getReference(),
								se.getEncadreur().getReference()) == null) {
							if (encadreurService.findByReference(se.getEncadreur().getReference()) == null) {
								encadreurService.save(se.getEncadreur());
							}
							se.setDateAffectation(DateUtil.getDate());
							se.setStage(findByReference(stage.getReference()));
							se.setEncadreur(encadreurService.findByReference(se.getEncadreur().getReference()));
							stageEncadrantService.save(se);
						}
						encadreurService.update(se.getEncadreur());
					}
				}
				if (stage.getStageMembreJuries().size() > 0) {
					for (StageMembreJury sm : stage.getStageMembreJuries()) {
						if (membreJuryService.findByReference(sm.getMembreJury().getReference()) == null) {
							membreJuryService.save(sm.getMembreJury());
							sm.setDateAffectation(DateUtil.getDate());
							sm.setStage(findByReference(stage.getReference()));
							sm.setMembreJury(membreJuryService.findByReference(sm.getMembreJury().getReference()));
							stageMembreJuryService.save(sm);
						} else {
							membreJuryService.update(sm.getMembreJury());
							sm.setDateAffectation(DateUtil.getDate());
							sm.setStage(findByReference(stage.getReference()));
							sm.setMembreJury(membreJuryService.findByReference(sm.getMembreJury().getReference()));
							stageMembreJuryService.update(sm);
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
			s.getStageEncadreurs().forEach(se -> {
				List<Stage> stages = findByEncadreur(se.getEncadreur().getId(), 0, 2).getContent();
				stageEncadrantService.removeById(se.getId());
				if (stages.size() == 1) {
					encadreurService.removeByReference(se.getEncadreur().getReference());
				}
			});
			s.getStageEtudiants().forEach(se -> {
				List<Stage> stages = findByEtudiant(se.getEtudiant().getId(), 0, 2).getContent();
				stageEtudiantService.removeById(se.getId());
				if (stages.size() == 1) {
					etudiantService.removeByCin(se.getEtudiant().getCin());
				}
			});
			s.getStageMembreJuries().forEach(sm -> {
				List<Stage> stages = findByJury(sm.getMembreJury().getId(), 0, 2).getContent();
				stageMembreJuryService.removeByid(sm.getId());
				if (stages.size() == 1) {
					membreJuryService.removeByReference(sm.getMembreJury().getReference());
				}
			});
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
	public Page<Stage> findByCoordinateurUserId(Long id, int page, int size, String sort) {
		if (sort.equals("asc")) {
			return stageDao.findByCoordinateurUserId(id, PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return stageDao.findByCoordinateurUserId(id, PageRequest.of(page, size, Sort.by(Direction.DESC, "id")));
		} else if (sort.equals("sujet")) {
			return stageDao.findByCoordinateurUserId(id, PageRequest.of(page, size, Sort.by(Direction.ASC, "sujet")));
		} else if (sort.equals("dateDebut")) {
			return stageDao.findByCoordinateurUserId(id,
					PageRequest.of(page, size, Sort.by(Direction.ASC, "dateDebut")));
		} else if (sort.equals("dateFin")) {
			return stageDao.findByCoordinateurUserId(id, PageRequest.of(page, size, Sort.by(Direction.ASC, "DateFin")));
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

	@Override
	public Stage findEtudiantActiveStage(Long id) {
		List<Stage> stages = stageDao.findByEtudiantUserId(id);
		Date date = DateUtil.getDate();
		System.out.println(stages.size());
		if (stages.size() > 0) {
			Stage activeStage = stages.stream().filter(stage -> stage.getDateDebut().getYear() == date.getYear())
					.collect(Collectors.toList()).get(0);
			return activeStage;
		} else {
			return null;
		}

	}

	@Override
	public List<Stage> findEncadreurActiveStages(Long id) {
		List<Stage> stages = stageDao.findByEncadreurUserId(id);
		Date date = DateUtil.getDate();
		List<Stage> activeStage = stages.stream().filter(stage -> stage.getDateDebut().getYear() == date.getYear())
				.collect(Collectors.toList());
		return activeStage;
	}

	@Override
	public List<Stage> findJuryActiveStages(Long id) {
		List<Stage> stages = stageDao.findByJuryUserId(id);
		Date date = DateUtil.getDate();

		List<Stage> activeStage = stages.stream().filter(stage -> stage.getDateDebut().getYear() == date.getYear())
				.collect(Collectors.toList());
		return activeStage;
	}

	@Override
	public List<Stage> findCoordinateurActiveStages(Long id) {
		List<Stage> stages = stageDao.findByCoordinateurUserId(id);
		Date date = DateUtil.getDate();
		List<Stage> activeStage = stages.stream().filter(stage -> stage.getDateDebut().getYear() == date.getYear())
				.collect(Collectors.toList());
		return activeStage;
	}

}
