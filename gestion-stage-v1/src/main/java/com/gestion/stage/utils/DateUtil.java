package com.gestion.stage.utils;

import java.util.Date;

public class DateUtil {
	
	public static Date getDate() {
		return new Date();
	}
	public static int compareDates(Date date1,Date date2) {
		if(date1.compareTo(date2)>0) {
			return -1;
		}else if(date1.compareTo(date2)<0) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
