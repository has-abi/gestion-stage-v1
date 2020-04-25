package com.gestion.stage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RapportTache implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepot;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification;
	private boolean valider;
	@OneToOne
	private Document document;
	@OneToOne
	private Tache tache;
}
