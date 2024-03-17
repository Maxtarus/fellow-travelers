package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {
    void save(Trip trip);
    List<Trip> findAllByDriverId(long id);
    List<Trip> findAll();
    List<Trip> findAllNotCompleted();
    List<Trip> findAllAvailableForPassenger(long passengerId);
    void deleteById(long id);
    Trip findById(long id);
}
