package com.gestion.stage.bean;

import java.util.Date;

import javax.persistence.Column;
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
public class Tache{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "Text")
	private String contenu;
	private String reference;
	private Date dateCreation;
	private Date dateLimite;
	private Date dateValidation;
	private boolean effectuer;
	private boolean valider;
	@ManyToOne
	private Stage stage;
	@ManyToOne
	private StageEncadreur stageEncadreur;

}
