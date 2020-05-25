package com.gestion.stage.bean;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Etudiant{

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
	private Filiere filiere;
	@OneToMany(mappedBy = "etudiant")
	private List<StageEtudiant> stageEtudiants;
	@OneToMany(mappedBy = "etudiant")
	private List<EtudiantDocument>  etudiantDocuments;
	
}
