package ru.sber.fellow_travelers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.fellow_travelers.entity.Trip;

import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findAllByDriverId(Long driverId);
}
