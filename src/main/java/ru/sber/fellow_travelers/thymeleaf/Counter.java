package ru.sber.fellow_travelers.thymeleaf;

public class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public int get() {
        return count;
    }

    public int incrementAndGet() {
        return ++count;
    }
}