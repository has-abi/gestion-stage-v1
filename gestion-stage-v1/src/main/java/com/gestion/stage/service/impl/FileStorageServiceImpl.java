package com.gestion.stage.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Etudiant;
import com.gestion.stage.bean.Stage;
import com.gestion.stage.bean.StageEtudiant;
import com.gestion.stage.service.facade.FileStorageService;
import com.gestion.stage.service.facade.StageService;
import com.gestion.stage.utils.ExcelUtil;
import com.gestion.stage.utils.FileUtil;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	private StageService stageService;

	private Path root;

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
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

	public ByteArrayInputStream loadPV(Long idCoord, String libelle) {
		List<Stage> foundedStages = stageService.findCoordinateurActiveStages(idCoord);
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		for (Stage s : foundedStages) {
			if (s.isStatu()) {
				for (StageEtudiant se : s.getStageEtudiants()) {
					etudiants.add(se.getEtudiant());
				}
			}
		}

		ByteArrayInputStream in = ExcelUtil.loadPvStages(etudiants, libelle);
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

}
