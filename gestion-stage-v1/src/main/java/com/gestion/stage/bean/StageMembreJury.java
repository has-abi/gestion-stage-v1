package com.gestion.stage.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
* @author  Hassan Abida & Aicha ELABDELLAOUI 
* @version 1.0
*/
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StageMembreJury{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "Text" )
	private String remarque;
	private String role;
	@Temporal(TemporalType.DATE)
	private Date dateAffectation;
	@ManyToOne
	private MembreJury membreJury;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Stage stage;

}
