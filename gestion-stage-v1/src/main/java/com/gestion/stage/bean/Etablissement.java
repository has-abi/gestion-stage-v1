package com.gestion.stage.bean;


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
public class Etablissement{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100,unique = true)
	private String libelle;
	@Column(length = 50)
	private String adress;
	@Column(length = 15,unique = true)
	private String tele_fix;
	@Column(length = 15,unique = true)
	private String tele_gsm;
	@Column(length = 50,unique = true)
	private String email;
	@Column(length = 25)
	private String doyen;
	@ManyToOne
	private Ville ville;
	
}
