package com.gestion.stage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Rapport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateDepot;
	@Temporal(TemporalType.DATE)
	private Date dateValidation;
	@Temporal(TemporalType.DATE)
	private Date dateSoutenance;
	@Column(columnDefinition = "Text")
	private String descreption;
	@OneToOne
	private Document document;
	
}
