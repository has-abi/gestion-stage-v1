package com.gestion.stage.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String nom;
	@Column(length = 60)
	private String prenom;
	@Column(length = 10)
	private String sexe;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(length = 15)
	private String tele;
	private String adress;
	@Email
	@Column(unique = true,length = 100,nullable = true)
	private String email;
	@NotNull
	@Column(length = 100)
	private String motPass;
	private String photo;
	private boolean active;
	private String question;
	private String reponce;
	@Temporal(TemporalType.DATE)
	private Date dateJoin;
	private int role;
	@OneToMany(mappedBy = "utilisateur")
	private List<SujetForum> sujetForums;
	@OneToMany(mappedBy = "utilisateur")
	private List<Commentaire> commentaires;
	
}
