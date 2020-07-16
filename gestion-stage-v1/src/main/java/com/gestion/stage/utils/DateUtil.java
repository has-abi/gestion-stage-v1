package com.gestion.stage.utils;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getDate() {
		return new Date();
	}

	public static int compareDates(Date date1, Date date2) {
		if (date1.compareTo(date2) > 0) {
			return -1;
		} else if (date1.compareTo(date2) < 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public static String anneeUniversitaire() {
		String annee = "";
		Date date = new Date();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = date.getMonth();
		if (month == 8 || month == 9 || month == 10 || month == 11) {
			annee = year + "/" + (year + 1);
		} else {
			annee = (year - 1) + "/" + year;
		}
		return annee;
	}
}
