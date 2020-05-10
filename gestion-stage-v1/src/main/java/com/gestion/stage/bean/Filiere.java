package com.gestion.stage.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;
import com.gestion.stage.bean.Etudiant;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Filiere{	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	@OneToMany(mappedBy ="filiere")
	private List<Etudiant> etudiants;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Departement departement;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private Coordinateur coordinateur;

	
	
}
