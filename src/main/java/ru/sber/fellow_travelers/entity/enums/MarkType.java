package ru.sber.fellow_travelers.entity.enums;

public enum MarkType {
    EXCELLENT(5),
    GOOD(4),
    NORMAL(3),
    EXPECTATIONS_NOT_FULFILLED(2),
    DISLIKE(1);

    private final int digitMark;

    MarkType(int digitMark) {
        this.digitMark = digitMark;
    }

    public int getDigitMark() {
        return digitMark;
    }
}
