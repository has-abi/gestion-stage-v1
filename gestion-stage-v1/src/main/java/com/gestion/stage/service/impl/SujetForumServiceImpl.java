package com.gestion.stage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Commentaire;
import com.gestion.stage.bean.SujetForum;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.SujetForumDao;
import com.gestion.stage.service.facade.CommentaireService;
import com.gestion.stage.service.facade.SujetForumService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;

@Service
public class SujetForumServiceImpl implements SujetForumService {
	@Autowired
	private SujetForumDao sujetForumDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentaireService commentaireService;

	@Override
	public int save(SujetForum sujetForum) {
		System.out.println(sujetForum);
		User u = userService.findByReference(sujetForum.getUser().getReference());
		if (u == null) {
			return -1;
		} else {
			System.out.println("else here we are");
			sujetForum.setUser(u);
			sujetForum.setDateCreation(DateUtil.getDate());
			sujetForumDao.save(sujetForum);
			return 1;
		}
	}

	@Override
	public List<SujetForum> findAll() {
		return sujetForumDao.findAll();
	}

	@Override
	public List<SujetForum> findByDateCreation(Date dateCreation) {
		return sujetForumDao.findByDateCreation(dateCreation);
	}

	@Override
	public List<SujetForum> findByDateModification(Date dateModification) {
		return sujetForumDao.findByDateModification(dateModification);
	}

	@Override
	public int remove(String reference) {
		SujetForum sujetFounded = findByReference(reference);
		if (sujetFounded == null) {
			return -1;
		} else {
			List<Commentaire> comments = commentaireService.findBySujetForumId(sujetFounded.getId());
			comments.forEach(c -> commentaireService.deleteById(c.getId()));
			sujetForumDao.delete(sujetFounded);
			return 1;
		}
	}

	@Override
	public SujetForum findByReference(String reference) {
		return sujetForumDao.findByReference(reference);
	}

	@Override
	public List<SujetForum> findByUserId(Long id) {
		return sujetForumDao.findByUserId(id);
	}

	@Override
	public Page<SujetForum> findAllWithPagination(int page, int size, String sort) {
		if (sort.equals("asc")) {
			return sujetForumDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "id")));
		} else if (sort.equals("desc")) {
			return sujetForumDao.findAll(PageRequest.of(page, size, Sort.by(Direction.DESC, "id")));
		} else if (sort.equals("dateCreation")) {
			return sujetForumDao.findAll(PageRequest.of(page, size, Sort.by(Direction.ASC, "dateCreation")));
		} else {
			return null;
		}

	}

	@Override
	public ResponseEntity<List<SujetForum>> searchForSujetForums(Specification<SujetForum> spec) {
		return new ResponseEntity<>(sujetForumDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	@Override
	public List<SujetForum> findByContentContains(String content) {
		return sujetForumDao.findByContentContains(content);
	}

	@Override
	public int update(SujetForum sujetForum) {
		User u = userService.findByUsername(sujetForum.getUser().getUsername());
		if(u == null) {
			return -1;
		}else {
			sujetForum.setDateModification(DateUtil.getDate());
			sujetForumDao.save(sujetForum);
			return 1;
		}
	}

	@Override
	public int countSujets() {
		return (int) sujetForumDao.count();
	}

}
