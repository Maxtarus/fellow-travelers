package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RequestStatus;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.exception.RequestNotFoundException;
import ru.sber.fellow_travelers.repository.RequestRepository;
import ru.sber.fellow_travelers.service.RequestService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void save(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<Request> findAll() {
        return (List<Request>) requestRepository.findAll();
    }

    @Override
    public Request findById(long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Request with id=" + id + " not found"));
    }

    @Override
    public List<Request> findAllByPassengerId(long id) {
        return findAll().stream()
                .filter(request -> request.getPassenger().getId() == id)
                .filter(request -> request.getTrip().getTripStatus().equals(TripStatus.NOT_COMPLETED))
                .collect(Collectors.toList());
    }

    @Override
    public List<Request> findAvailableByDriverId(long id) {
        return findAll().stream()
                .filter(request -> request.getTrip().getDriver().getId() == id)
                .filter(request -> request.getTrip().getFreeSeats() > 0)
                .filter(request -> request.getRequestStatus().equals(RequestStatus.UNDER_CONSIDERATION))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public void createRequest(User user, Trip trip, int passengersNumber) {
        Request request = new Request();
        request.setRequestStatus(RequestStatus.UNDER_CONSIDERATION)
                .setPassenger(user)
                .setTrip(trip)
                .setPassengersNumber(passengersNumber);
        save(request);
    }

    @Override
    public void approveRequest(Request request) {
        request.setRequestStatus(RequestStatus.APPROVED);
        save(request);
    }

    @Override
    public void disapproveRequest(Request request) {
        request.setRequestStatus(RequestStatus.REJECTED);
        save(request);
    }

    @Override
    public List<Request> findAllApprovedForCompletedTripsByPassenger(User passenger) {
        return requestRepository.findAllByPassengerId(passenger.getId(), TripStatus.COMPLETED, RequestStatus.APPROVED);
    }

    @Override
    public List<Request> findAllApprovedForCompletedTripByTripId(long id) {
        return findAll().stream()
                .filter(request -> request.getTrip().getId() == id)
                .filter(request -> request.getTrip().getTripStatus().equals(TripStatus.COMPLETED))
                .filter(request -> request.getRequestStatus().equals(RequestStatus.APPROVED))
                .collect(Collectors.toList());
    }
}
