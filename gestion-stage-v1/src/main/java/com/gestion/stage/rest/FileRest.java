package com.gestion.stage.rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.gestion.stage.bean.Coordinateur;
import com.gestion.stage.bean.FileInfo;
import com.gestion.stage.service.facade.CoordinateurService;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.utils.DateUtil;
import com.gestion.stage.utils.ResponseMessage;
import com.google.common.net.HttpHeaders;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@RestController
@RequestMapping
@CrossOrigin({ "http://localhost:4200" })
public class FileRest {

	@Autowired
	private FileStorageService storageService;
	@Autowired
	private CoordinateurService coordinateurService;

	@GetMapping("/pv/coordinateur/id/{id}")
	public ResponseEntity<Resource> getFileXlsx(@PathVariable Long id) {
		Coordinateur coord = coordinateurService.findByUserId(id);
		String annee = DateUtil.anneeUniversitaire();
		String[] title = coord.getFiliere().getLibelle().split(" ");

		String filename = "";
		for (String t : title) {
			filename += t;
		}
		filename += "_Pv_" + annee;
		System.out.println(filename);
		InputStreamResource file = new InputStreamResource(storageService.loadPV(id, coord.getFiliere().getLibelle()));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(file);
	}

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

}
