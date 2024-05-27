package ru.sber.fellow_travelers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.fellow_travelers.entity.Review;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;

import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Optional<Review> findByFromUserAndToUserAndTrip(User fromUser, User toUser, Trip trip);
}
