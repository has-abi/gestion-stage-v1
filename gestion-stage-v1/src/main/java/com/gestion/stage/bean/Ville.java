package com.gestion.stage.bean;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ville{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String nom;
	private int codePostal;

	@ManyToOne
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Pays pays;
	@OneToMany(mappedBy = "ville")
	private List<Etablissement> etablissements;
	@OneToMany(mappedBy = "ville")
	private List<OrganismeAccueil> organismeAccueils;
	
}
