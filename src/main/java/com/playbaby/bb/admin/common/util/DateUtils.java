package com.playbaby.bb.admin.common.util;

import java.time.LocalDate;

public class DateUtils {
    public static String startOfDay(LocalDate localDate) {
        return localDate != null ? localDate.toString() + " 00:00:00" : null;
    }

    public static String endOfDay(LocalDate localDate) {
        return localDate != null ? localDate.toString() + " 23:59:59" : null;
    }
}
