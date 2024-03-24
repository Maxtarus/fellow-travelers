package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;

import java.util.List;
import java.util.Optional;

public interface MarkService {
    void save(Mark mark);
    List<Mark> findAll();
    void rateAfterTrip(User toUser, User fromUser, Trip trip, Mark mark);
    List<Mark> findAllForTripByTripId(long id);
}
