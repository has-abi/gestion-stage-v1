package com.gestion.stage.bean;

import java.io.Serializable;

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
public class OrganismeAccueil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String raisonSociale;
	@Column(length = 40)
	private String email;
	@Column(length = 15)
	private String tele;
	private String adress;
	@ManyToOne
	private Ville ville;
	@ManyToOne
	private TypeOrganisme typeOrganisme;
	@ManyToOne
	private TypeServiceOrganisme typeServiceOrganisme;
}
