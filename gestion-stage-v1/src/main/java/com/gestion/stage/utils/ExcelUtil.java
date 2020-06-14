package com.gestion.stage.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.stage.bean.Etudiant;

public class ExcelUtil {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "N°APOG", "NOMS","", "PRENOMS","", "Entrep", "Rapp", "Present", "Moy" };
	static String SHEET = "etudiants";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static ByteArrayInputStream loadPvStages(List<Etudiant> etudiants,String libelle) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			XSSFSheet sheet = (XSSFSheet) workbook.createSheet(SHEET);
			sheet.addIgnoredErrors(new CellRangeAddress(0,9999,0,9999),IgnoredErrorType.NUMBER_STORED_AS_TEXT,IgnoredErrorType.FORMULA,IgnoredErrorType.FORMULA_RANGE,IgnoredErrorType.CALCULATED_COLUMN, IgnoredErrorType.EVALUATION_ERROR);
			//arial font
			Font arial = workbook.createFont();
			arial.setFontName("Arial");
			//styles 
			CellStyle  centerStyle =  workbook.createCellStyle();
			centerStyle.setAlignment(HorizontalAlignment.CENTER);
			centerStyle.setFont(arial);
			// fonts
			
			XSSFCellStyle  style = (XSSFCellStyle) workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Font font = workbook.createFont();
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(true);
			style.setFont(font);
			
			
			XSSFFont titlesFonts = (XSSFFont) workbook.createFont();
			XSSFCellStyle titlesStyle = (XSSFCellStyle) workbook.createCellStyle();
			titlesFonts.setBold(true);
			titlesFonts.setFontName("Arial");
			titlesFonts.setColor((short)Font.COLOR_NORMAL);
			titlesFonts.setFontHeightInPoints((short) 14);
			titlesStyle.setFont(titlesFonts);
			
			XSSFRow pv = sheet.createRow(0);
			XSSFCell  cel1 = pv.createCell(3);
			cel1.setCellValue("UNIVERSITE CADI AYAD");
			cel1.setCellStyle(centerStyle);

			XSSFRow pv1 = sheet.createRow(1);
			XSSFCell cel2 = pv1.createCell(1);
			cel2.setCellValue("FACULTE DES SCIENCES ET TECHNIQUE");
			CellStyle s = workbook.createCellStyle();
			Font f = workbook.createFont();
			f.setFontName("Arial");
			f.setBold(true);
			s.setFont(f);
			cel2.setCellStyle(s);
			
			XSSFRow pv2 = sheet.createRow(2);
			XSSFCell cel3 = pv2.createCell(3);
			cel3.setCellValue("GUELIZ-MARRAKECH ");
			cel3.setCellStyle(centerStyle);
			
			XSSFRow pv3 = sheet.createRow(4);
			XSSFCell cel4 = pv3.createCell(1);
			cel4.setCellValue("PV de jury du module");
			cel4.setCellStyle(titlesStyle);

			XSSFRow pv4 = sheet.createRow(5);
			XSSFCell cel5 = pv4.createCell(1);
			XSSFCell cel51 = pv4.createCell(3);
			cel51.setCellValue(libelle);
			cel5.setCellValue("Filiere:");
			cel5.setCellStyle(titlesStyle);
			cel51.setCellStyle(titlesStyle);

			XSSFRow pv5 = sheet.createRow(6);
			XSSFCell cel6 = pv5.createCell(1);
			XSSFCell cel61 = pv5.createCell(3);
			cel61.setCellValue("Semestre 6");
			cel6.setCellValue("Semestre:");
			cel6.setCellStyle(titlesStyle);
			cel61.setCellStyle(titlesStyle);
			
			XSSFRow pv6 = sheet.createRow(7);
			XSSFCell cel7 = pv6.createCell(1);
			XSSFCell cel71 = pv6.createCell(3);
			cel7.setCellValue("Module:");
			cel71.setCellValue("Projet de fin d'études");
			cel7.setCellStyle(titlesStyle);
			cel71.setCellStyle(titlesStyle);
			
			XSSFRow pvAnnee = sheet.createRow(8);
			XSSFCell cellAnnee = pvAnnee.createCell(6);
			cellAnnee.setCellValue("Année Universitaire : "+DateUtil.anneeUniversitaire());
			cellAnnee.setCellStyle(centerStyle);
			
			
			XSSFCellStyle perStyle = (XSSFCellStyle) workbook.createCellStyle();
			perStyle.setAlignment(centerStyle.getAlignment());
			 XSSFColor color = new XSSFColor( Color.YELLOW );
			perStyle.setFillForegroundColor(color);
			perStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			perStyle.setFont(f);
			perStyle.setBorderBottom(BorderStyle.MEDIUM);
			perStyle.setBorderTop(BorderStyle.MEDIUM);
			perStyle.setBorderLeft(BorderStyle.MEDIUM);
			perStyle.setBorderRight(BorderStyle.MEDIUM);
			
			XSSFRow row8 = sheet.createRow(9);
			XSSFCell cell1 = row8.createCell(5);
			XSSFCell cell2 = row8.createCell(6);
			XSSFCell cell3 = row8.createCell(7);
			cell1.setCellType(CellType.STRING);
			cell2.setCellType(CellType.STRING);
			cell3.setCellType(CellType.STRING);
			cell1.setCellValue("40%");
			cell2.setCellValue("30%");
			cell3.setCellValue("30%");
			cell1.setCellStyle(perStyle);
			cell2.setCellStyle(perStyle);
			cell3.setCellStyle(perStyle);


		
			// Header
			XSSFCellStyle headerStyle =(XSSFCellStyle) workbook.createCellStyle();
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			headerStyle.setBorderBottom(BorderStyle.MEDIUM);
			headerStyle.setBorderLeft(BorderStyle.MEDIUM);
			headerStyle.setBorderRight(BorderStyle.MEDIUM);
			headerStyle.setBorderTop(BorderStyle.MEDIUM);
			headerStyle.setFont(f);
			XSSFRow headerRow = sheet.createRow(10);
			// Create cells
			
			
			for (int i = 0; i < HEADERs.length; i++) {
				XSSFCell cell;
				cell = headerRow.createCell(i);	
				cell.setCellValue(HEADERs[i]);
				if(i == 5 || i == 6 || i == 7) {
					cell.setCellStyle(perStyle);
				}else {
					
					cell.setCellStyle(headerStyle);
				}
				
			}

			
			XSSFCellStyle dataStyle =(XSSFCellStyle) workbook.createCellStyle();
			dataStyle.setBorderBottom(BorderStyle.THIN);
			dataStyle.setBorderRight(BorderStyle.THIN);
			dataStyle.setBorderLeft(BorderStyle.THIN);
			dataStyle.setBorderTop(BorderStyle.THIN);
			dataStyle.setFont(arial);
			
			XSSFCellStyle dataValueStyle =(XSSFCellStyle) workbook.createCellStyle();
			dataValueStyle.setAlignment(HorizontalAlignment.CENTER);
			dataValueStyle.setBorderBottom(BorderStyle.THIN);
			dataValueStyle.setBorderTop(BorderStyle.THIN);
			dataValueStyle.setBorderLeft(BorderStyle.MEDIUM);
			dataValueStyle.setBorderRight(BorderStyle.MEDIUM);
			dataValueStyle.setFont(arial);
			
		
			int rowIdx = 11;
			for (Etudiant e : etudiants) {
				XSSFRow row = sheet.createRow(rowIdx++);
				XSSFCell dataCell1 = row .createCell(0);
				XSSFCell dataCell2 = row .createCell(1);
				XSSFCell dataCell3 = row .createCell(2);
				XSSFCell dataCell4 = row .createCell(3);
				XSSFCell dataCell5 = row .createCell(4);
				XSSFCell dataCell6 = row .createCell(5);
				XSSFCell dataCell7 = row .createCell(6);
				XSSFCell dataCell8 = row .createCell(7);
				XSSFCell dataCell9 = row .createCell(8);
				
				dataCell1.setCellType(CellType.STRING);
				dataCell6.setCellType(CellType.NUMERIC);
				dataCell6.setCellErrorValue(FormulaError.NUM);
				
				dataCell7.setCellType(CellType.NUMERIC);
				dataCell7.setCellErrorValue(FormulaError.NUM);
				
				dataCell8.setCellType(CellType.NUMERIC);
				dataCell8.setCellErrorValue(FormulaError.NUM);
				
				dataCell9.setCellType(CellType.NUMERIC);
				dataCell9.setCellFormula("SUM(F"+rowIdx+"*0.4+G"+rowIdx+"*0.3+H"+rowIdx+"*0.3)");
				dataCell9.setCellValue("0.00");
				
				dataCell1.setCellValue(e.getCodeAppoge());
				dataCell2.setCellValue(e.getUser().getNom());
				dataCell3.setCellValue("");
				dataCell4.setCellValue(e.getUser().getPrenom());
				dataCell5.setCellValue("");
				
				dataCell1.setCellStyle(dataStyle);
				dataCell2.setCellStyle(dataStyle);
				dataCell3.setCellStyle(dataStyle);
				dataCell4.setCellStyle(dataStyle);
				dataCell5.setCellStyle(dataStyle);
				dataCell6.setCellStyle(dataValueStyle);
				dataCell7.setCellStyle(dataValueStyle);
				dataCell8.setCellStyle(dataValueStyle);
				dataCell9.setCellStyle(dataValueStyle);
			}
			for(int i = 10;i<etudiants.size()+11;i++) {
				sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 2));
				sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 4));
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}
