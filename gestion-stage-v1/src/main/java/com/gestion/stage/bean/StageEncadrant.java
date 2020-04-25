package com.gestion.stage.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class StageEncadrant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@Column(columnDefinition = "Text")
	private String remarque;
	@ManyToOne
	private ProfileEncadrant profileEncadrant;
	@ManyToOne
	private Stage stage;
}
