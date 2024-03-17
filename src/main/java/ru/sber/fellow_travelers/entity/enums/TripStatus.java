package ru.sber.fellow_travelers.entity.enums;

public enum TripStatus {
    COMPLETED("Завершена"),
    NOT_COMPLETED("Не завершена");

    private final String title;

    TripStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
