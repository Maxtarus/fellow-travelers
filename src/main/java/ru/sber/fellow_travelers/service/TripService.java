package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;

import java.util.List;
import java.util.Optional;

public interface TripService {
    void save(Trip trip);
    List<Trip> findAllNotCompletedByDriverId(long id);
    List<Trip> findAll();
    List<Trip> findAllAvailableForPassenger(User passenger);
    void deleteById(long id);
    Trip findById(long id);
    void decrementFreeSeats(Trip trip);
    void completeTrip(Trip trip);
    List<Trip> findAllCompletedByDriverId(long id);
}
