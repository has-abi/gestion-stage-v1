package com.gestion.stage.service;

import java.util.List;

import com.gestion.stage.bean.ProfileEncadrant;

public interface ProfileEncadrantService {
	List<ProfileEncadrant> findByProfession(String profession);
	List<ProfileEncadrant> findAll();
	int save(ProfileEncadrant profileEncadrant);

}
