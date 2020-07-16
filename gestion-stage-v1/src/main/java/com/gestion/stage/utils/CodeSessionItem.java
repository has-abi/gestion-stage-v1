package com.gestion.stage.utils;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public class CodeSessionItem {
	private String username;
	private String code;
	private Long duration;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public CodeSessionItem(String username, String code, Long duration) {
		super();
		this.username = username;
		this.code = code;
		this.duration = duration;
	}

	public CodeSessionItem() {
		super();
		// TODO Auto-generated constructor stub
	}
}
