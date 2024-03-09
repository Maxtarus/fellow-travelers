package ru.sber.fellow_travelers.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class LocalDateUtils {
    private LocalDateUtils() { }

    public static LocalDate convertToISO(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH));
    }
}
