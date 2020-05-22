package com.gestion.stage.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Stage{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String reference;
	private String sujet;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private boolean statu;
	
	
	@ManyToOne
	private OrganismeAccueil organismeAccueil;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@OneToMany(mappedBy = "stage")
	private List<StageEtudiant> stageEtudiants;
	@OneToMany(mappedBy = "stage")
	private List<StageEncadreur> stageEncadreurs;
	@OneToMany(mappedBy = "stage")
	private List<StageMembreJury> stageMembreJuries;
}
