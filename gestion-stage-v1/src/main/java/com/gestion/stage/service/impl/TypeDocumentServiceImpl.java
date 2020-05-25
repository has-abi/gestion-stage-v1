package com.gestion.stage.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.TypeDocument;
import com.gestion.stage.dao.TypeDocumentDao;
import com.gestion.stage.service.facade.TypeDocumentService;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService{
	@Autowired
	private TypeDocumentDao typeDocumentDao;

	@Override
	public TypeDocument findByRef(String ref) {
		return typeDocumentDao.findByRef(ref);
	}

	@Override
	@Transactional
	public int deleteByRef(String ref) {
		TypeDocument type=findByRef(ref);
		if(type==null) {
			return -1;
		}else {
			typeDocumentDao.delete(type);
			return 1;
		}
	
	}

	@Override
	public int save(TypeDocument typeDocument) {
		TypeDocument typeFounded=findByRef(typeDocument.getRef());
		if(typeFounded!=null) {
			return -1;
		}else {
			typeDocumentDao.save(typeDocument);
			return 1;
		}
	}

	@Override
	public List<TypeDocument> findAll() {

		return typeDocumentDao.findAll();
	}

}
