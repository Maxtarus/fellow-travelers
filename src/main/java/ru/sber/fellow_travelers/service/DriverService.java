package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.User;

public interface DriverService {
    String calculateAverageMark(User driver);
    long getCompletedTripsAmount(User driver);
    int calculateAge(User driver);
}
