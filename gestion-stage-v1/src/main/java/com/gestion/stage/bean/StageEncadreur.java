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
public class StageEncadreur{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "Text")
	private String remarque;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Encadreur encadreur;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Stage stage;
}
