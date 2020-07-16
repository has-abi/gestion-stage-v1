package com.gestion.stage.utils;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public class VilleStatistics {
	private String nom;
	private int nombreVille;

	public VilleStatistics() {
		super();
	}

	public VilleStatistics(String nom, int nombreVille) {
		this.nom = nom;
		this.nombreVille = nombreVille;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNombreVille() {
		return nombreVille;
	}

	public void setNombreVille(int nombreVille) {
		this.nombreVille = nombreVille;
	}

	@Override
	public String toString() {
		return "VilleStatistics [nom=" + nom + ", nombreVille=" + nombreVille + "]";
	}

}
