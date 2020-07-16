package com.gestion.stage.utils;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public class OrganismeStatistics {

	private String nom;
	private int nombreOrganisme;

	public OrganismeStatistics(String nom, int nombreOrganisme) {
		super();
		this.nom = nom;
		this.nombreOrganisme = nombreOrganisme;
	}

	public OrganismeStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNombreOrganisme() {
		return nombreOrganisme;
	}

	public void setNombreOrganisme(int nombreOrganisme) {
		this.nombreOrganisme = nombreOrganisme;
	}

	@Override
	public String toString() {
		return "OrganismeStatistics [nom=" + nom + ", nombreOrganisme=" + nombreOrganisme + "]";
	}

}
