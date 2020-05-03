package com.gestion.stage.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class StageEncadrant{

	private Long id;
	@Column(columnDefinition = "Text")
	private String remarque;
	@ManyToOne
	private ProfileEncadrant profileEncadrant;
	@ManyToOne
	private Stage stage;
}
