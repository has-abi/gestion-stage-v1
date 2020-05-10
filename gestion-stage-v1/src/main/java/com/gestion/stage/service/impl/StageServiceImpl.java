package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.bean.StageMembreJury;
import com.gestion.stage.bean.TypeStage;
import com.gestion.stage.dao.StageDao;
import com.gestion.stage.service.EncadreurService;
import com.gestion.stage.service.EtudiantService;
import com.gestion.stage.service.MembreJuryService;
import com.gestion.stage.service.OrganismeAccueilService;
import com.gestion.stage.service.StageEncadrantService;
import com.gestion.stage.service.StageEtudiantService;
import com.gestion.stage.service.StageMembreJuryService;
import com.gestion.stage.service.StageService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;
@Service
public class StageServiceImpl implements StageService{
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
	public List<Stage> findByDateDebut(Date dateDebut) {
		return stageDao.findByDateDebut(dateDebut);
	}

	@Override
	public List<Stage> findByDateFin(Date dateFin) {
		return stageDao.findByDateFin(dateFin);
	}

	@Override
	public List<Stage> findByTypeStage(TypeStage typeStage) {
		return stageDao.findByTypeStage(typeStage);
	}

	@Override
	public List<Stage> findByDateFinBetween(Date date1, Date date2) {
		return stageDao.findByDateFinBetween(date1, date2);
	}

	@Override
	public List<Stage> findBySujetContains(String sujet) {
		return stageDao.findBySujetContains(sujet);
	}

	@Override
	public List<Stage> findByOrganismeAccueilRaisonSocial(String raisonSocial) {
		return stageDao.findByOrganismeAccueilRaisonSociale(raisonSocial);
	}

	@Override
	public Stage findByid(Long id) {
		return stageDao.findById(id).get();
	}

	@Override
	public int save(Stage stage) {
		if(FieldsUtil.StageFields(stage)<0) {
			return -1;
		}else if(findByReference(stage.getReference())!=null) {
			return -2;
		}else if(DateUtil.compareDates(stage.getDateDebut(), stage.getDateFin())<=0){
			return -3;
		}else {
			if(stage.getOrganismeAccueil() != null) {
				organismeAccueilService.save(stage.getOrganismeAccueil());
				OrganismeAccueil oa = organismeAccueilService.findByRaisonSocial(stage.getOrganismeAccueil().getRaisonSociale());
				stage.setOrganismeAccueil(oa);
			}
			stage.setStatu(false);
			stage.setDateCreation(DateUtil.getDate());
			stageDao.save(stage);
			if(stage.getStageEtudiants() != null) {
				for(StageEtudiant se : stage.getStageEtudiants()) {
					if(etudiantService.findByCin(se.getEtudiant().getCin()) == null) {
						etudiantService.save(se.getEtudiant());
					}
					se.setStage(findByReference(stage.getReference()));
					se.setEtudiant(etudiantService.findByCin(se.getEtudiant().getCin()));
					se.setDateAffectation(DateUtil.getDate());
					stageEtudiantService.save(se);
				}
			}
			if(stage.getStageEncadreurs() !=null) {
				for(StageEncadreur se : stage.getStageEncadreurs()) {
					if(encadreurService.findByReference(se.getEncadreur().getReference()) == null) {
						encadreurService.save(se.getEncadreur());
					}
					se.setDateAffectation(DateUtil.getDate());
					se.setStage(findByReference(stage.getReference()));
					se.setEncadreur(encadreurService.findByReference(se.getEncadreur().getReference()));
					stageEncadrantService.save(se);
				}
			}
		
			if(stage.getStageMembreJuries() != null) {
				for(StageMembreJury sm : stage.getStageMembreJuries()) {
					if(membreJuryService.findByReference(sm.getMembreJury().getReference()) == null) {
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
		if(findByReference(stage.getReference()) == null) {
			return -1;
		}else {
			if(FieldsUtil.StageFields(stage)<0) {
				return -2;
			}else {
				if(stage.getOrganismeAccueil() != null) {
					organismeAccueilService.save(stage.getOrganismeAccueil());
					OrganismeAccueil oa = organismeAccueilService.findByRaisonSocial(stage.getOrganismeAccueil().getRaisonSociale());
					stage.setOrganismeAccueil(oa);
				}
				stageDao.save(stage);
				return 1;
			}
		}
	}
	@Transactional
	@Override
	public int removeByReference(String reference) {
			Stage s = findByReference(reference);
			if(s != null) {
			s.getStageEncadreurs().forEach(se->stageEncadrantService.removeById(se.getId()));
			s.getStageEtudiants().forEach(se->stageEtudiantService.removeById(se.getId()));
			s.getStageMembreJuries().forEach(sm->stageMembreJuryService.removeByid(sm.getId()));
			stageDao.delete(s);
			return 1;
		}else {
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

}
