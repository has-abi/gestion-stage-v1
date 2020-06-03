package com.gestion.stage.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Filiere;
import com.gestion.stage.bean.User;
import com.gestion.stage.dao.EtudiantDao;
import com.gestion.stage.service.facade.EtudiantService;
import com.gestion.stage.service.facade.FiliereService;
import com.gestion.stage.service.facade.RoleService;
import com.gestion.stage.service.facade.UserService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.FieldsUtil;

@Service
public class EtudiantServiceImpl implements EtudiantService {
	@Autowired
	private EtudiantDao etudiantDao;
	@Autowired
	private UserService userService;
	@Autowired
	private FiliereService filiereService;
	@Autowired
	private RoleService roleService;

	@Override
	public Etudiant findByCin(String cin) {
		return etudiantDao.findByCin(cin);
	}

	@Override
	public Etudiant findByCodeAppoge(String codeAppoge) {
		return etudiantDao.findByCodeAppoge(codeAppoge);
	}

	@Override
	public List<Etudiant> findByFiliere(Filiere filiere) {
		return etudiantDao.findByFiliere(filiere);
	}

	@Override
	public int save(Etudiant etudiant) {
		if (FieldsUtil.etudiantFields(etudiant) < 0) {
			return -1;
		} else {
			Etudiant foundedEtudByCin = findByCin(etudiant.getCin());
			Etudiant foundeEtudByCode = findByCodeAppoge(etudiant.getCodeAppoge());
			Filiere filiere = filiereService.findById(etudiant.getFiliere().getId());
			if (foundedEtudByCin != null) {
				return -2;
			} else if (foundeEtudByCode != null) {
				return -3;
			} else if (filiere == null) {
				return -4;
			} else {
				etudiant.getUser().setReference("u" + DateUtil.getDate().getTime());
				etudiant.getUser().setRole(roleService.getEtudiantRole());
				etudiant.getUser().setActive(false);
				if (userService.save(etudiant.getUser()) < 0) {
					return -5;
				}

				etudiant.setUser(userService.findByReference(etudiant.getUser().getReference()));
				etudiantDao.save(etudiant);
				return 1;
			}
		}
	}

	@Override
	public Page<Etudiant> findAll(int page,int size,String sort) {
		if(sort.equals("asc")) {
			return etudiantDao.findAll(PageRequest.of(page, size,Sort.by(Direction.ASC,"id")));	
		}else if(sort.equals("desc")) {
			return etudiantDao.findAll(PageRequest.of(page, size,Sort.by(Direction.DESC,"id")));
		}else {
			return null;
		}
		
	}

	@Override
	public int Update(Etudiant etudiant) {
		Etudiant etud = etudiantDao.findById(etudiant.getId()).get();
		if (etud == null) {
			return -1;
		} else if (FieldsUtil.etudiantFields(etudiant) < 0) {
			return -2;
		} else {
			List<Etudiant> etuds = etudiantDao.findAll();
			for(Etudiant e : etuds) {
				if(e.getId()!=etudiant.getId() && (e.getCin().equals(etudiant.getCin()) || e.getCodeAppoge().equals(etudiant.getCodeAppoge()))) {
					return -3;
				}
			}
			etudiantDao.save(etudiant);
			return 1;
		}
	}

	@Transactional
	@Override
	public int removeByCin(String cin) {
		Etudiant etud = findByCin(cin);
		if (etud == null) {
			return -1;
		} else {
			User u = etud.getUser();
			etudiantDao.delete(etud);
			userService.removeById(u.getId());
			return 1;
		}
	}

	@Override
	public List<Etudiant> readXsl() throws IOException {
		InputStream xlsFile = new ClassPathResource("pv_jury.xlsx").getInputStream();
		List<Etudiant> tempStudentList = new ArrayList<Etudiant>();
		XSSFWorkbook workbook = new XSSFWorkbook(xlsFile);
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Etudiant tempStudent = new Etudiant();

			XSSFRow row = worksheet.getRow(i);

//			tempStudent.setCodeAppoge(row.getCell(0).getStringCellValue());
//			tempStudent.setNom(row.getCell(1).getStringCellValue());
//			tempStudent.setPrenom(row.getCell(2).getStringCellValue());
//			tempStudentList.add(tempStudent);
		}
		return tempStudentList;
	}

	
	public void writeXsl() throws IOException  {
		List<Etudiant> etudiants=new ArrayList<Etudiant>();
		//create a workbook
		 XSSFWorkbook workbook = new XSSFWorkbook();
		 //create a spreadSheet
		 XSSFSheet sheet = workbook.createSheet("etudiant Data");
		 //create a Row Object
		 XSSFRow row;
		//create cells
		 row=sheet.createRow(0);
		 Cell cell0=row.createCell(0);
		 Cell cell1=row.createCell(1);
		 Cell cell2=row.createCell(2);
		 Cell cell3=row.createCell(3);
		 cell0.setCellValue("CodeAppoge");
		 cell1.setCellValue("Nom");
		 cell2.setCellValue("Prenom");
		 cell3.setCellValue("moyen");
		 //create cell style
		 CellStyle style=workbook.createCellStyle();
		 style.setAlignment(HorizontalAlignment.CENTER);
		 style.setVerticalAlignment(VerticalAlignment.CENTER);
		 //for each cell
		 cell0.setCellStyle(style);
		 cell1.setCellStyle(style);
		 cell2.setCellStyle(style);
		 cell3.setCellStyle(style);
		 //create rows and cells for data
		 for(int i=0;i<etudiants.size() ;i++) {
			 row=sheet.createRow(i+1);
			 for(int j=0;j<4;j++) {
				 Cell cell=row.createCell(j);
				 cell.setCellStyle(style);
			 }
		 }
		
//		 // writing the created  excel file
//		  try{
//		   FileInputStream file = new FileInputStream(new File("pv_jury.xlsx"));
//		   workbook.write(file);
//		   fie.close();
//		  
//		  } catch (FileNotFoundException e) {
//			  
//	           Logger.getLoggerContext(ExportExcel.class.getName()).log(Level.SEVERE,null,e);
//	           }
		 
		 
		 
		 
		 
	}

	@Override
	public Etudiant findByUserEmail(String email) {
		return etudiantDao.findByUserEmail(email);
	}

	@Override
	public Etudiant findByUserId(Long id) {
		return etudiantDao.findByUserId(id);
	}

	@Override
	public Page<Etudiant> findAllWithPaginition(int page, int size) {
		return etudiantDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Etudiant> findByUserNomContainsOrUserPrenomContains(String nom, String prenom, int page, int size) {
		return etudiantDao.findByUserNomContainsOrUserPrenomContains(nom, prenom, PageRequest.of(page, size));
	}

	@Override
	public Page<Etudiant> findByNiveau(String niveau, int page, int size) {
		return etudiantDao.findByNiveau(niveau, PageRequest.of(page, size));
	}

	@Override
	public ResponseEntity<List<Etudiant>> searchForEtudiants(Specification<Etudiant> spec) {
		return new ResponseEntity<>(etudiantDao.findAll(Specification.where(spec)), HttpStatus.OK);
	}

	@Override
	public Page<Etudiant> findByCoordinateur(long id, int page, int size,String sort) {
		if(sort.equals("asc")) {
			return etudiantDao.findByCoordinateur(id, PageRequest.of(page, size,Sort.by(Sort.Direction.ASC,"id")));
		}else if(sort.equals("desc")) {
			return etudiantDao.findByCoordinateur(id, PageRequest.of(page, size,Sort.by(Sort.Direction.DESC,"id")));
		}else if(sort.equals("nom")){
			return etudiantDao.findByCoordinateur(id, PageRequest.of(page, size,Sort.by(Sort.Direction.DESC,"nom")));
		}else{
			return null;
		}
		
	}

}
