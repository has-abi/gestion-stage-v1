package com.gestion.stage.utils;

public class LoginUser {
	private String username;
	private String password;
	private String cne;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", cne=" + cne + "]";
	}
	public LoginUser(String username, String password, String cne) {
		super();
		this.username = username;
		this.password = password;
		this.cne = cne;
	}
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
