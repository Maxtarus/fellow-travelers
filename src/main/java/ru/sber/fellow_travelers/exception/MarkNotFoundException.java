package ru.sber.fellow_travelers.exception;

public class MarkNotFoundException extends RuntimeException {
    public MarkNotFoundException(String message) {
        super(message);
    }
}
