package ru.sber.fellow_travelers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;

import java.util.Optional;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
    Optional<Mark> findByFromUserAndToUserAndTrip(User fromUser, User toUser, Trip trip);
}
