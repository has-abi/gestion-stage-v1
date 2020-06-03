package com.gestion.stage.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.gestion.stage.bean.FileInfo;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.utils.Convention;
import com.gestion.stage.utils.ResponseMessage;
import com.google.common.net.HttpHeaders;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class FileRest {
	@Autowired
	FileStorageService storageService;
	@Autowired
	private StageService stageService;
	



	@GetMapping("/file/display/{filename:.+}")
	public ResponseEntity<Resource> displayFile(@PathVariable String filename) throws IOException {
		Resource file = storageService.loadDocs(filename);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(file.getInputStream()));
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.save(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FileRest.class, "getFile", path.getFileName().toString()).build().toString();
			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadDocs(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/report/id/{id}")
	public void report(@PathVariable Long id, HttpServletResponse response) {
		List<Convention> conventions = new ArrayList<Convention>();
		Convention c = new Convention();
		Stage s = stageService.findByid(id);
		c.setOrganisme_nom(s.getOrganismeAccueil().getRaisonSociale());
		c.setOrganisme_adress(s.getOrganismeAccueil().getAdress());
		c.setOrganisme_tele(s.getOrganismeAccueil().getTele());
		conventions.add(c);
		System.out.println(conventions.size());
		try {
			File file = ResourceUtils.getFile("classpath:static/Convention_de_stage1.jrxml");

			InputStream input = new FileInputStream(file);

			// Compile the Jasper report from .jrxml to .japser

			JasperReport jasperReport = JasperCompileManager.compileReport(input);

			// Get your data source

			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(conventions);

			// Add parameters

			Map<String, Object> parameters = new HashMap<>();

			parameters.put("organisme_nom",s.getOrganismeAccueil().getRaisonSociale());
			parameters.put("organisme_tele",s.getOrganismeAccueil().getRaisonSociale());
			parameters.put("organisme_adress",s.getOrganismeAccueil().getRaisonSociale());
			// Fill the report

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, source);

			// Export the report to a PDF file

			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
			System.out.println("PDF File Generated !!");


		} catch (Exception e) {

			System.out.println(e.getMessage()); 

		}

	}
}
