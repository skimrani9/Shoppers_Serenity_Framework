package com.shoppers.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Date formatting and manipulation utilities for test data.
 */
public final class DateUtils {

    private DateUtils() {
    }

    public static String today(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String plusDays(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }
}
