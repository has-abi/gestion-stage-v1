package com.gestion.stage.service.facade;

import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRDataSource;

public interface FileStorageService {

	public void save(MultipartFile file);

	public Resource loadDocs(String filename);
	public Resource loadPics(String filename);
	public void deleteAll();

	public Stream<Path> loadAll();
	public byte[] generatePDFReport(String inputFileName, Map<String, Object> params,
		    JRDataSource dataSource);
	public byte[] report(Long id);
}
