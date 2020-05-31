package com.gestion.stage.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String getExt(MultipartFile file) {
		String[] fileFrags = file.getOriginalFilename().split("\\.");
		String extension = fileFrags[fileFrags.length - 1];
		return extension;
	}

	public static String getEditedName(MultipartFile file) {
		Date date = new Date();
		String nom = "FST_rapport_de_stage" + date.getTime() + "." + getExt(file);
		return nom;
	}

	public static String getFileName(MultipartFile file) {
		String[] fileFrags = file.getOriginalFilename().split("\\.");
		String nom = "";
		for (int i = 0; i < fileFrags.length - 1; i++) {
			nom += fileFrags[i];
		}
		return nom;
	}

	public static Path getPathFormFile(MultipartFile file) {
		Path root;
		String ext = getExt(file);
		if (ext.equals("pdf") || ext.equals("docx")) {
			root = Paths.get("uploads/documents");
		} else if (ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg")) {
			root = Paths.get("uploads/profile_pictures");
		}else {
			root = Paths.get("uploads");
		} return root;
	}

	public static MultipartFile getNewFile(String fileName, MultipartFile currentFile) {
		return new MultipartFile() {
			@Override
			public String getName() {
				return currentFile.getName();
			}

			@Override
			public String getOriginalFilename() {
				return fileName;
			}

			@Override
			public String getContentType() {
				return currentFile.getContentType();
			}

			@Override
			public boolean isEmpty() {
				return currentFile.isEmpty();
			}

			@Override
			public long getSize() {
				return currentFile.getSize();
			}

			@Override
			public byte[] getBytes() throws IOException {
				return currentFile.getBytes();
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return currentFile.getInputStream();
			}

			@Override
			public void transferTo(File file) throws IOException, IllegalStateException {

			}
		};
	}
}
