package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.repository.TripRepository;
import ru.sber.fellow_travelers.service.TripService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void save(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public List<Trip> findAllNotCompletedByDriverId(long id) {
        return tripRepository.findAllByDriverId(id)
                .stream()
                .filter(trip -> trip.getTripStatus().equals(TripStatus.NOT_COMPLETED))
                .collect(Collectors.toList());
    }

    @Override
    public List<Trip> findAll() {
        return (List<Trip>) tripRepository.findAll();
    }

    @Override
    public List<Trip> findAllAvailableForPassenger(User passenger,
                                                   Integer passengersNumber,
                                                   String startPoint, String finalPoint,
                                                   LocalDate departureDate) {
        List<Trip> foundedTrips = findTrips(passengersNumber, startPoint, finalPoint, departureDate);
        List<Trip> availableForPassengerTrips = new ArrayList<>();

        for (Trip trip : foundedTrips) {
            boolean flag = !trip.getDriver().equals(passenger);

            for (Request request : trip.getRequests()) {
                if (request.getPassenger().equals(passenger)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                availableForPassengerTrips.add(trip);
            }
        }

        return availableForPassengerTrips;
    }

    @Override
    public void deleteById(long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public Trip findById(long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip with id=" + id + "not found"));
    }

    @Override
    public void decrementFreeSeats(Trip trip) {
        trip.setFreeSeats(trip.getFreeSeats() - 1);
        save(trip);
    }

    @Override
    public void completeTrip(Trip trip) {
        trip.setTripStatus(TripStatus.COMPLETED);
        save(trip);
    }

    @Override
    public List<Trip> findAllCompletedByDriverId(long id) {
        return tripRepository.findAllByDriverId(id)
                .stream()
                .filter(trip -> trip.getTripStatus().equals(TripStatus.COMPLETED))
                .collect(Collectors.toList());
    }


    private List<Trip> findTrips(Integer passengersNumber,
                                String startPoint, String finalPoint,
                                LocalDate departureDate) {
        return tripRepository.findTrips(passengersNumber, startPoint, finalPoint, departureDate);
    }


    private List<Trip> findAllNotCompletedAndWithFreeSeats() {
        return findAll()
                .stream()
                .filter(trip -> trip.getTripStatus().equals(TripStatus.NOT_COMPLETED))
                .filter(trip -> trip.getFreeSeats() > 0)
                .collect(Collectors.toList());
    }

}
