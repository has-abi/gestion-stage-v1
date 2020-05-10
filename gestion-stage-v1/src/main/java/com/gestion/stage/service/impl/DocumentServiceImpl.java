package com.gestion.stage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.stage.bean.Document;
import com.gestion.stage.dao.DocumentDao;
import com.gestion.stage.service.DocumentService;

@Service
public class DocumentServiceImpl  implements DocumentService{
	@Autowired
	private DocumentDao documentDao;

	@Override
	public Document findByTitre(String titre) {
		return documentDao.findByTitre(titre);
	}

	@Override
	public Document findByReference(String reference) {
		return documentDao.findByReference(reference);
	}

	@Override
	public int deleteByReference(String reference) {
	Document documentFounded=findByReference(reference);
	if(documentFounded==null) {return -1;
	}else {
		documentDao.delete(documentFounded);
		return 1;
	}
	}

	@Override
	public int save(Document document) {
		Document documentFounded=findByReference(document.getReference());
		if(documentFounded!=null) {return -1;
		}else {
			documentDao.save(document);
			return 1;
		}

	}

	@Override
	public List<Document> findAll() {
		return documentDao.findAll();
	}

}
