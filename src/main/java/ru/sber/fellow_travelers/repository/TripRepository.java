package ru.sber.fellow_travelers.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.enums.TripStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findAllByDriverId(Long driverId);
    @Query(value = """
        SELECT *
        FROM trips t
        WHERE t.status = 'NOT_COMPLETED' AND
            t.free_seats >= :passengers_number AND
            t.start_point LIKE %:start_point% AND
            t.final_point LIKE %:final_point% AND
            DATE(t.departure_time) = :departure_date
            
    """, nativeQuery = true)
    List<Trip> findTrips(@Param("passengers_number") Integer passengersNumber,
                        @Param("start_point") String startPoint,
                        @Param("final_point") String finalPoint,
                        @Param("departure_date") LocalDate departureDate
                        );
}
