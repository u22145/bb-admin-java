package com.playbaby.bb.admin.common.util.validator.impl;

import com.playbaby.bb.admin.common.util.validator.DateValidator;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;

public class DateValidatorUsingLocalDate implements DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidatorUsingLocalDate(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    @Override
    public String transform(String dateStr, DateTimeFormatter dtf) {
        if (StringUtils.isBlank(dateStr))
            return null;

        try {
            return LocalDate.parse(dateStr, this.dateFormatter).format(dtf);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            return null;
        }
    }
}
