package com.gestion.stage.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class StageItem {
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@ManyToOne
	private ProfileEtudiant profileEtudiant;
	@ManyToOne
	private ProfileEncadreurEtablissement profileEncadreurEtablissement;
	@ManyToOne
	private ProfileEncadreurOrganisme profileEncadreurOrganisme;
	@ManyToOne
	private Stage stage;
}
