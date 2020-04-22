package com.gestion.stage.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ProfileEtudiant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true,length = 10)
	private String cin;
	@Column(length = 8)
	private String codeAppoge;
	@Column(length = 20)
	private String nationalite;
	@Column(length = 20)
	private String niveau;
	@Column(length = 15)
	private String Situation_familial;
	@OneToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Specialite specialite;
	@ManyToOne
	private Departement departement;
	
	
}
