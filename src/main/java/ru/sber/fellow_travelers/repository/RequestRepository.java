package ru.sber.fellow_travelers.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RequestStatus;
import ru.sber.fellow_travelers.entity.enums.TripStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    @Query(value =
            "SELECT request " +
            "FROM Request request " +
                "JOIN request.passenger rp ON rp.id =:passengerId " +
                "JOIN request.trip " +
//                "JOIN request.trip.marks rtm ON request.trip.id = rtm.trip.id " +
            "WHERE request.trip.status = :tripStatus " +
                "AND request.status = :requestStatus"
    )
    List<Request> findAllByPassengerId(@Param("passengerId") long passengerId,
                                       @Param("tripStatus") TripStatus tripStatus,
                                       @Param("requestStatus") RequestStatus requestStatus);
}
