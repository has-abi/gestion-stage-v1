package com.gestion.stage.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.dao.EtudiantDao;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.utils.ExcelUtil;
import com.gestion.stage.utils.FileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
@Service
public class FileStorageServiceImpl implements FileStorageService{
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	private StageService stageService;
	@Autowired
	private EtudiantDao etudiantService;
	private  Path root ;
	

	  @Override
	  public void save(MultipartFile file) {
		 this.root = FileUtil.getPathFormFile(file);
	    try {
	      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }



	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	  public ByteArrayInputStream loadPV() {
		    List<Etudiant> tutorials = etudiantService.findAll();

		    ByteArrayInputStream in = ExcelUtil.tutorialsToExcel(tutorials);
		    return in;
		  }

	@Override
	public Resource loadDocs(String filename) {
		this.root = Paths.get("uploads/documents");
		try {
		      Path file = root.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
	}

	@Override
	public Resource loadPics(String filename) {
		this.root = Paths.get("uploads/profile_pictures");
		try {
		      Path file = root.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
	}



	@Override
	public byte[] generatePDFReport(String inputFileName, Map<String, Object> params, JRDataSource dataSource) {
		byte[] bytes = null;
	    JasperReport jasperReport = null;
	    File fileToload;
	    try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
			fileToload = new ClassPathResource("static/"+inputFileName).getFile();
	      // Check if a compiled report exists
	        jasperReport = (JasperReport) JRLoader.loadObject(fileToload);
	      // Compile report from source and save
	     
	      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
	      // return the PDF in bytes
	      bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    }
	    catch (JRException | IOException e) {
	      e.printStackTrace();
	    }
	    return bytes;
	}



	@Override
	public byte[] report(Long id) {
		byte[] bytes = null;
		try {
			InputStream employeeReportStream = new ClassPathResource("static/Convention_de_stage1.jrxml").getInputStream();
			JasperReport jasperReport;
//			File jasperFile = ResourceUtils.getFile("classpath:static/Convention_de_stage1.japser");

		
				jasperReport = JasperCompileManager.compileReport(employeeReportStream);
//				JRSaver.saveObject(jasperReport, "Convention_de_stage1.jasper");
			
//				jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
			
			Map<String, Object> parameters = new HashMap<>();
			Stage stage = stageService.findByid(id);
			System.out.println(stage.getOrganismeAccueil());
			parameters.put("organisme_nom", stage.getOrganismeAccueil().getRaisonSociale());
			parameters.put("organisme_adress",stage.getOrganismeAccueil().getAdress());
			parameters.put("organisme_tele",stage.getOrganismeAccueil().getTele());
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					  jasperReport, parameters);
			
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException |IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bytes);
		return bytes;
	}
	
	






}
