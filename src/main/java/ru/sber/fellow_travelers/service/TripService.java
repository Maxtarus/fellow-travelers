package ru.sber.fellow_travelers.service;


import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface TripService {
    void save(Trip trip);
    List<Trip> findAllNotCompletedByDriverId(long id);
    List<Trip> findAll();

    List<Trip> findAllAvailableForPassenger(User passenger,
                                            Integer passengersNumber,
                                            String startPoint, String finalPoint,
                                            LocalDate departureDate);

    void deleteById(long id);
    Trip findById(long id);
    void decrementFreeSeats(Trip trip);
    void completeTrip(Trip trip);
    List<Trip> findAllCompletedByDriverId(long id);
}
