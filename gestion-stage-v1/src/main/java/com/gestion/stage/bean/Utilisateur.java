package com.gestion.stage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String nom;
	@Column(length = 20)
	private String prenom;
	@Column(length = 10)
	private String sexe;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(unique = true,length = 14)
	private String tele;
	private String adress;
	@Column(unique = true,length = 40)
	private String email;
	@Column(length = 40)
	private String motPass;
	private String photo;
	private boolean active;
	
}
