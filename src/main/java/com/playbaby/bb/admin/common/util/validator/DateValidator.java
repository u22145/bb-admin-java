package com.playbaby.bb.admin.common.util.validator;

import java.time.format.DateTimeFormatter;

public interface DateValidator {
    boolean isValid(String dateStr);
    String transform(String dateStr, DateTimeFormatter dtf);
}
