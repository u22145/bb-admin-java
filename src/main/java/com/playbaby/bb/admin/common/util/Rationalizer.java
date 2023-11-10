package com.playbaby.bb.admin.common.util;

import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.validator.GuildValidator;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public class Rationalizer {

    public static void rationalizeInt(Object o, String[] fromFields) {
        rationalizeInt(o, fromFields, false);
    }

    public static void rationalizeInt(Object o, String[] fromFields, boolean logError) {
        Class<?> clazz = o.getClass();
        Set<String> fieldNames = new HashSet<>();
        Stream.of(clazz.getDeclaredFields()).forEach(f -> fieldNames.add(f.getName()));
        for (String fromField : fromFields) {
            String toField = fromField + "Int";
            if (fieldNames.contains(toField))
                try {
                    rationalizeInt(o, fromField, toField);
                } catch (Exception e ) {
                    if (logError)
                        log.error("when converting {} for field {}, an error occurred {}",
                                fromField, toField, e.getMessage(), e);
                }
        }
    }

    private static void rationalizeInt(Object o, String fromField, String toField) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = o.getClass();
        Field fromFld = clazz.getDeclaredField(fromField);
        fromFld.setAccessible(true);
        Object v = fromFld.get(o);

        try {
            if (v != null) {
                Field toFld = clazz.getDeclaredField(toField);
                toFld.setAccessible(true);
                String val = v.toString();
                toFld.set(o, Integer.parseInt(val));
                toFld.setAccessible(false);
            }
        } finally {
            fromFld.setAccessible(false);
        }
    }

    public static BigDecimal rationalizeCommission(Object o, String commissionField, int code) throws APIException {
        Field field = null;

        try {
            Class<?> clazz = o.getClass();
            field = clazz.getDeclaredField(commissionField + "Commission");
            field.setAccessible(true);
            Object v = field.get(o);
            BigDecimal commission = GuildValidator.checkCommission(v != null ? v.toString() : null);
            if (commission != null)
                return commission;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error("commission field {} does not exist in {}", commissionField, o, e);
        } finally {
            if (field != null)
                field.setAccessible(false);
        }

        throw APIException.getInstance(code, commissionField + " commission must be > 0 and < 1");
    }
}
