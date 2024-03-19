package ru.sber.fellow_travelers.entity.enums;

public enum RequestStatus {
    APPROVED("Одобрена"),
    REJECTED("Отклонена"),
    UNDER_CONSIDERATION("На рассмотрении");
    private final String title;

    RequestStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
