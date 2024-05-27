package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.Review;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;

import java.util.List;

public interface ReviewService {
    void save(Review review);
    List<Review> findAll();
    Review findById(long id);
    void rateAfterTrip(User toUser, User fromUser, Trip trip, Review review, MarkType mark);
}
