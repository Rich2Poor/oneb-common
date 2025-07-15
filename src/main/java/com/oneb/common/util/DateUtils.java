package com.oneb.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Date and time utility methods.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
public final class DateUtils {
    
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
    private static final DateTimeFormatter ISO_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(ISO_DATETIME_FORMAT);
    
    private DateUtils() {
        // Utility class
    }
    
    /**
     * Get current timestamp as Instant.
     */
    public static Instant now() {
        return Instant.now();
    }
    
    /**
     * Get current date as LocalDate.
     */
    public static LocalDate today() {
        return LocalDate.now();
    }
    
    /**
     * Get current date and time as LocalDateTime.
     */
    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }
    
    /**
     * Convert Instant to LocalDateTime using system default timezone.
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
    
    /**
     * Convert LocalDateTime to Instant using system default timezone.
     */
    public static Instant toInstant(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
    
    /**
     * Convert Date to Instant.
     */
    public static Instant toInstant(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant();
    }
    
    /**
     * Convert Instant to Date.
     */
    public static Date toDate(Instant instant) {
        if (instant == null) {
            return null;
        }
        return Date.from(instant);
    }
    
    /**
     * Format LocalDate to string using default format.
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DEFAULT_DATE_FORMATTER);
    }
    
    /**
     * Format LocalDateTime to string using default format.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DEFAULT_DATETIME_FORMATTER);
    }
    
    /**
     * Format Instant to string using default format.
     */
    public static String formatInstant(Instant instant) {
        if (instant == null) {
            return null;
        }
        return toLocalDateTime(instant).format(DEFAULT_DATETIME_FORMATTER);
    }
    
    /**
     * Format LocalDateTime to ISO string.
     */
    public static String formatToIso(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(ISO_DATETIME_FORMATTER);
    }
    
    /**
     * Parse date string using default format.
     */
    public static LocalDate parseDate(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        try {
            return LocalDate.parse(dateString, DEFAULT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Parse datetime string using default format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        if (StringUtils.isEmpty(dateTimeString)) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeString, DEFAULT_DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Parse ISO datetime string.
     */
    public static LocalDateTime parseIsoDateTime(String dateTimeString) {
        if (StringUtils.isEmpty(dateTimeString)) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeString, ISO_DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Calculate days between two dates.
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * Calculate hours between two datetimes.
     */
    public static long hoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return 0;
        }
        return ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }
    
    /**
     * Calculate minutes between two datetimes.
     */
    public static long minutesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return 0;
        }
        return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
    }
    
    /**
     * Check if date is today.
     */
    public static boolean isToday(LocalDate date) {
        return date != null && date.equals(LocalDate.now());
    }
    
    /**
     * Check if date is yesterday.
     */
    public static boolean isYesterday(LocalDate date) {
        return date != null && date.equals(LocalDate.now().minusDays(1));
    }
    
    /**
     * Check if date is tomorrow.
     */
    public static boolean isTomorrow(LocalDate date) {
        return date != null && date.equals(LocalDate.now().plusDays(1));
    }
    
    /**
     * Get start of day for given date.
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay();
    }
    
    /**
     * Get end of day for given date.
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atTime(23, 59, 59, 999999999);
    }
    
    /**
     * Get start of month for given date.
     */
    public static LocalDate startOfMonth(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.withDayOfMonth(1);
    }
    
    /**
     * Get end of month for given date.
     */
    public static LocalDate endOfMonth(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.withDayOfMonth(date.lengthOfMonth());
    }
    
    /**
     * Check if year is leap year.
     */
    public static boolean isLeapYear(int year) {
        return Year.isLeap(year);
    }
    
    /**
     * Get age from birth date.
     */
    public static int getAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
