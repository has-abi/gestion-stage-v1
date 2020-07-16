package com.gestion.stage.service.facade;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public interface FileStorageService {

	public void save(MultipartFile file);

	public Resource loadDocs(String filename);

	public Resource loadPics(String filename);

	public Stream<Path> loadAll();

	ByteArrayInputStream loadPV(Long idCoord, String libelle);
}
