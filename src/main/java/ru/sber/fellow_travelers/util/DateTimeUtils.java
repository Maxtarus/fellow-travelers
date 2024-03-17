package ru.sber.fellow_travelers.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateTimeUtils {
    private DateTimeUtils() { }

    public static LocalDate toISO(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH));
    }

    public static String convertToInputFormat(String date) {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static LocalDateTime toLocalDateTime(String date, String time) {
        String dateTime = date + " " + time;
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public static String fromLocalDateTimeToStringDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String fromLocalDateTimeToStringTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
