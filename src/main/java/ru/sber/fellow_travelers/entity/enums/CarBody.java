package ru.sber.fellow_travelers.entity.enums;

public enum CarBody {
    SEDAN("седан"),
    UNIVERSAL("универсал"),
    HATCHBACK("хэтчбек"),
    PICKUP("пикап"),
    SUV("внедорожник"),
    CUV("кроссовер"),
    MINIVAN("минивэн"),
    CABRIOLET("кабриолет"),
    MINIBUS("микроавтобус");

    private final String title;

    CarBody(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
