package com.gestion.stage.utils;

import org.apache.logging.log4j.util.Strings;

import com.gestion.stage.bean.Administrateur;
import com.gestion.stage.bean.Encadreur;
import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.MembreJury;
import com.gestion.stage.bean.OrganismeAccueil;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.bean.User;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public class FieldsUtil {

	public static int etudiantFields(Etudiant etudiant) {
		if (Strings.isEmpty(etudiant.getCin()) || Strings.isEmpty(etudiant.getCodeAppoge())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int utilisateurFields(User user) {
		if (Strings.isEmpty(user.getUsername()) || Strings.isEmpty(user.getPassword()) || Strings.isEmpty(user.getNom())
				|| Strings.isEmpty(user.getPrenom())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int encadreurFields(Encadreur encadreur) {
		if (Strings.isEmpty(encadreur.getType()) || Strings.isEmpty(encadreur.getQualite())
				|| Strings.isEmpty(encadreur.getReference())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int juryFields(MembreJury membreJury) {
		if (Strings.isEmpty(membreJury.getReference())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int adminFields(Administrateur administrateur) {
		if (Strings.isEmpty(administrateur.getRef()) || Strings.isEmpty(administrateur.getProfession())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int OrganismeFields(OrganismeAccueil organismeAccueil) {
		if (Strings.isEmpty(organismeAccueil.getRaisonSociale()) || Strings.isEmpty(organismeAccueil.getAdress())
				|| Strings.isEmpty(organismeAccueil.getEmail())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int StageEncadreurFields(StageEncadreur stageEncadreur) {
		if (stageEncadreur.getEncadreur().getReference() == null || stageEncadreur.getEncadreur().getReference() == ""
				|| stageEncadreur.getStage().getId() == 0 || stageEncadreur.getStage().getId() == null) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int StageFields(Stage stage) {
		if (stage.getDateDebut() == null || stage.getDateFin() == null || Strings.isEmpty(stage.getReference())) {
			return -1;
		} else {
			return 1;
		}
	}

	public static int StageEtudiantFields(StageEtudiant stageEtudiant) {
		if (Strings.isEmpty(stageEtudiant.getEtudiant().getCin())
				|| Strings.isEmpty(stageEtudiant.getEtudiant().getCodeAppoge())
				|| stageEtudiant.getStage().getId() == null || stageEtudiant.getStage().getId() == 0) {
			return -1;
		} else {
			return 1;
		}
	}

}
