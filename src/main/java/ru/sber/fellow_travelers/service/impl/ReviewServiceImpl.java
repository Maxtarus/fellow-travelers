package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Review;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.repository.ReviewRepository;
import ru.sber.fellow_travelers.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    @Override
    public void rateAfterTrip(User toUser, User fromUser, Trip trip, Review review, MarkType mark) {
        review.setToUser(toUser)
                .setFromUser(fromUser)
                .setMarkType(mark)
                .setTrip(trip);
        save(review);
    }

    @Override
    public Review findById(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip with id=" + id + "not found"));
    }

}
