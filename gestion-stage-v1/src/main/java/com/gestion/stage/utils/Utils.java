package com.gestion.stage.utils;

import java.util.Date;

public class Utils {

    public static int checkDate(Date dateToCheck) {
        Date date = new Date();
        if (dateToCheck.compareTo(date) > 0) {
            return -1;
        } else if (dateToCheck.compareTo(date) < 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
