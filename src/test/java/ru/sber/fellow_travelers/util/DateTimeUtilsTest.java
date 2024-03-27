package ru.sber.fellow_travelers.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeUtilsTest {

    @Test
    void testToISO() {
        String dateStr = "01.01.2022";
        LocalDate expected = LocalDate.of(2022, 1, 1);
        LocalDate result = DateTimeUtils.toISO(dateStr);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToInputFormat() {
        String dateStr = "2022-01-01";
        String expected = "01.01.2022";
        String result = DateTimeUtils.convertToInputFormat(dateStr);
        assertEquals(expected, result);
    }

    @Test
    void testToLocalDateTime() {
        String dateStr = "01.01.2022";
        String timeStr = "12:00";
        LocalDateTime expected = LocalDateTime.of(2022, 1, 1, 12, 0);
        LocalDateTime result = DateTimeUtils.toLocalDateTime(dateStr, timeStr);
        assertEquals(expected, result);
    }

    @Test
    void testFromLocalDateTimeToStringDate() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        String expected = "01.01.2022";
        String result = DateTimeUtils.fromLocalDateTimeToStringDate(dateTime);
        assertEquals(expected, result);
    }

    @Test
    void testFromLocalDateTimeToStringTime() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        String expected = "12:00";
        String result = DateTimeUtils.fromLocalDateTimeToStringTime(dateTime);
        assertEquals(expected, result);
    }
}