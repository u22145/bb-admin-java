package com.playbaby.bb.admin.common.util.validator;

import com.playbaby.bb.admin.common.entity.io.CommonIO;
import com.playbaby.bb.admin.common.util.validator.impl.DateValidatorUsingLocalDate;
import org.apache.logging.log4j.util.Strings;

import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.StringUtils.isBlank;

class CommonValidator {
    private static DateValidator yyyyMMdd;
    static {
        yyyyMMdd = new DateValidatorUsingLocalDate(DateTimeFormatter.BASIC_ISO_DATE);
    }

    // yyyyMMdd
    static boolean isDateValid(String dateStr) {
        return yyyyMMdd.isValid(dateStr);
    }

    static void validateDate(CommonIO commonIO) {
        // nothing to do
        if (commonIO == null)
            return;
        // nothing to do
        String from = commonIO.getFrom();
        String to = commonIO.getTo();
        if (isBlank(from) && isBlank(to))
            return;

        String fromDate = yyyyMMdd.transform(from, DateTimeFormatter.ISO_LOCAL_DATE);
        String toDate = yyyyMMdd.transform(to, DateTimeFormatter.ISO_LOCAL_DATE);

        // nothing to do
        if (fromDate == null && toDate == null)
            return;

        if (fromDate == null) {
            // we ony set the toDate portion
            commonIO.setToDate(toDate + " 23:59:59");
        } else if (toDate == null) {
            // we ony set the fromDate portion
            commonIO.setFromDate(fromDate + " 00:00:00");
        } else if (from.compareTo(to) <= 0) {
            commonIO.setFromDate(fromDate + " 00:00:00");
            commonIO.setToDate(toDate + " 23:59:59");
        } else {
            // do date swap
            commonIO.setFromDate(toDate + " 00:00:00");
            commonIO.setToDate(fromDate + " 23:59:59");
        }
    }

    static void validateSort(CommonIO commonIO) {
        if (commonIO == null)
            return;

        String sort = commonIO.getSort();
        commonIO.setSortStr(Strings.isBlank(sort) || sort.equals("up") ? "asc" : "desc");
    }

//    public static void validatePage(CommonIO commonIO) {
//        // nothing to do
//        if (commonIO == null)
//            return;
//
//        try {
//            commonIO.setPageInt(Integer.parseInt(commonIO.getPage()));
//        } catch (NumberFormatException e) {
//            commonIO.setPageInt(1);
//        }
//    }
}
