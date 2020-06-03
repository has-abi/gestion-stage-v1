package com.gestion.stage.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Etudiant;

public class ExcelUtil {
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Code d'appogé", "Nom","Prenom","Entrep" ,"Rapp","Present" , "Moyen"};
	  static String SHEET = "etudiants";

	  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static ByteArrayInputStream tutorialsToExcel(List<Etudiant> etudiants) {

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);
	      
	      
	      //fonts
	      CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		   // style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    Font font = workbook.createFont();
	            font.setColor(IndexedColors.BLACK.getIndex());
	            style.setFont(font);
	      
	      Row pv=sheet.createRow(0);
	      Cell cel1=pv.createCell(1);
	      cel1.setCellValue("UNIVERSITE CADI AAYAD");
	      
	      Row pv1=sheet.createRow(1);
	      Cell cel2=pv1.createCell(1);
	      cel2.setCellValue("FACULTE DES SCIENCES ET TECHNIQUE");
	      
	      Row pv2=sheet.createRow(2);
	      Cell cel3=pv2.createCell(1);
	      cel3.setCellValue("GUELIZ-MARRAKECH ");
	      
	      Row pv3 =sheet.createRow(4);
	      Cell cel4=pv3.createCell(1);
	      cel4.setCellValue("PV de jury du module");
	      
	      Row pv4 =sheet.createRow(5);
	      Cell cel5=pv4.createCell(1);
	      cel5.setCellValue("Filiere:  LST SIR");
	      
	      Row pv5 =sheet.createRow(6);
	      Cell cel6=pv5.createCell(1);
	      cel6.setCellValue("Semestre:S6");
	      
	      Row pv6 =sheet.createRow(7);
	      Cell cel7=pv6.createCell(1);
	      cel7.setCellValue("Module:Projet de fin d'études");
	      
	      Row row8=sheet.createRow(9);
	      Cell cell1=row8.createCell(3);
	      Cell cell2=row8.createCell(4);
	      Cell cell3=row8.createCell(5);
	      cell1.setCellValue("40%");
	      cell2.setCellValue("30%");
	      cell3.setCellValue("30%");
	      cell1.setCellStyle(style);
	      cell2.setCellStyle(style);
	      cell3.setCellStyle(style);	      
	      
	    //Create a Font for styling header cells
	      Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.BLACK.getIndex());
          
	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	      // Header
	      Row headerRow = sheet.createRow(10);
	      // Create cells
	        for(int i = 0; i < HEADERs.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(HEADERs[i]);
	            cell.setCellStyle(headerCellStyle);
	        }

	      
	    

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 11;
	      for (Etudiant e: etudiants) {
	        Row row = sheet.createRow(rowIdx++);

	        row.createCell(0).setCellValue(e.getCodeAppoge());
	        row.createCell(1).setCellValue(e.getUser().getNom());
	        row.createCell(2).setCellValue(e.getUser().getPrenom());
	      }

	      
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }
}

