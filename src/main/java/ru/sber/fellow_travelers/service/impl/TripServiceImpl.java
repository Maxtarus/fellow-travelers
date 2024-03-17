package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.repository.RequestRepository;
import ru.sber.fellow_travelers.repository.TripRepository;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.TripService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final RequestRepository requestRepository;

    public TripServiceImpl(TripRepository tripRepository, RequestRepository requestRepository) {
        this.tripRepository = tripRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public void save(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public List<Trip> findAllByDriverId(long id) {
        return tripRepository.findAllByDriverId(id);
    }

    @Override
    public List<Trip> findAll() {
        return (List<Trip>) tripRepository.findAll();
    }

    @Override
    public List<Trip> findAllNotCompleted() {
        return findAll()
                .stream()
                .filter(trip -> trip.getStatus().equals(TripStatus.NOT_COMPLETED))
                .collect(Collectors.toList());
    }

    @Override
    public List<Trip> findAllAvailableForPassenger(long id) {
        List<Trip> notCompletedTrips = findAllNotCompleted();
        List<Trip> availableForPassengerTrips = new ArrayList<>();
        User user = AuthUtils.getUserFromContext();

        for (Trip trip : notCompletedTrips) {
            boolean flag = true;
            for (Request request : trip.getRequests()) {
                if (!request.getPassenger().equals(user)) {
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

}
