package com.gestion.stage.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEncadreur;
import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.bean.StageMembreJury;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public class AppFilters {

	public static List<Stage> filterStagesByCoord(Long id, List<Stage> stages) {
		return stages.stream().filter(s -> s.getCoordinateur().getUser().getId() == id).collect(Collectors.toList());
	}

	public static List<Stage> filterStagesByEncadreur(Long id, List<Stage> stages) {
		return stages.stream().filter(s -> checkEncadreur(id, s.getStageEncadreurs())).collect(Collectors.toList());
	}

	public static List<Stage> filterStagesByEtudiant(Long id, List<Stage> stages) {
		return stages.stream().filter(s -> checkEtudiant(id, s.getStageEtudiants())).collect(Collectors.toList());
	}

	public static List<Stage> filterStagesByJury(Long id, List<Stage> stages) {
		return stages.stream().filter(s -> checkJury(id, s.getStageMembreJuries())).collect(Collectors.toList());
	}

	public static boolean checkEncadreur(Long id, List<StageEncadreur> stageEncads) {
		boolean check = false;
		for (StageEncadreur se : stageEncads) {
			if (se.getEncadreur().getUser().getId() == id) {
				check = true;
			}
		}
		return check;
	}

	public static boolean checkEtudiant(Long id, List<StageEtudiant> stageEtuds) {
		boolean check = false;
		for (StageEtudiant se : stageEtuds) {
			if (se.getEtudiant().getUser().getId() == id) {
				check = true;
			}
		}
		return check;
	}

	public static boolean checkJury(Long id, List<StageMembreJury> stageEncads) {
		boolean check = false;
		for (StageMembreJury se : stageEncads) {
			if (se.getMembreJury().getUser().getId() == id) {
				check = true;
			}
		}
		return check;
	}

}