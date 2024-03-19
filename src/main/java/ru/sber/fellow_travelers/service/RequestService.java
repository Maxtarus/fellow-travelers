package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;

import java.util.List;

public interface RequestService {
    void save(Request request);
    List<Request> findAll();
    Request findById(long id);
    List<Request> findAllByPassengerId(long id);
    List<Request> findAvailableByDriverId(long id);
    void deleteById(long id);
    void createRequest(User user, Trip trip);
    void approveRequest(Request request);
    void disapproveRequest(Request request);
    List<Request> findAllApprovedForCompletedTrips(User passenger);
}
