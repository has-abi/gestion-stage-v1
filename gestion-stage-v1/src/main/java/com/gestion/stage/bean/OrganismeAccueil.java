package com.gestion.stage.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class OrganismeAccueil{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String raisonSociale;
	@Column(length = 40)
	private String email;
	@Column(length = 15)
	private String tele;
	private String adress;
	private String responsable;
	@ManyToOne
	private Ville ville;
	@ManyToOne
	private TypeOrganisme typeOrganisme;
	@ManyToOne
	private TypeServiceOrganisme typeServiceOrganisme;
	
}
