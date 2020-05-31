package com.gestion.stage.service.facade;

import java.nio.file.Path;
import java.util.stream.Stream;
import java.io.ByteArrayInputStream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {

	public void save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();
	ByteArrayInputStream loadPV();
}
