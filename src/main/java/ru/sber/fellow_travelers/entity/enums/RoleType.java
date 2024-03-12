package ru.sber.fellow_travelers.entity.enums;

public enum RoleType {
    ADMIN("Администратор"),
    PASSENGER("Пассажир"),
    DRIVER("Водитель");

    private String title;

    RoleType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
