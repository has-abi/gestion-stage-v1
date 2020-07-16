package com.gestion.stage.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author Hassan Abida & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String reference;
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
	@Column(unique = true, length = 100, nullable = true)
	private String username;
	@Column(length = 100)
	private String password;
	private String photo;
	private boolean active;
	private String question;
	private String reponce;
	@Temporal(TemporalType.DATE)
	private Date dateJoin;
	private boolean confirm;
	private String codeConfirm;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponce() {
		return reponce;
	}

	public void setReponce(String reponce) {
		this.reponce = reponce;
	}

	public Date getDateJoin() {
		return dateJoin;
	}

	public void setDateJoin(Date dateJoin) {
		this.dateJoin = dateJoin;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public String getCodeConfirm() {
		return codeConfirm;
	}

	public void setCodeConfirm(String codeConfirm) {
		this.codeConfirm = codeConfirm;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public User(Long id, String reference, String nom, String prenom, String sexe, Date dateNaissance, String tele,
			String adress, @Email String username, String password, String photo, boolean active, String question,
			String reponce, Date dateJoin, List<Role> roles) {
		super();
		this.id = id;
		this.reference = reference;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.tele = tele;
		this.adress = adress;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.active = active;
		this.question = question;
		this.reponce = reponce;
		this.dateJoin = dateJoin;
		this.roles = roles;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", reference=" + reference + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe
				+ ", dateNaissance=" + dateNaissance + ", tele=" + tele + ", adress=" + adress + ", username="
				+ username + ", password=" + password + ", photo=" + photo + ", active=" + active + ", question="
				+ question + ", reponce=" + reponce + ", dateJoin=" + dateJoin + ", roles=" + roles + "]";
	}

}
