package ru.sber.fellow_travelers.mapper;

import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.util.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TripMapper {
    public Trip toEntity(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setStartPoint(tripDTO.getStartPoint());
        trip.setFinalPoint(tripDTO.getFinalPoint());
        trip.setDepartureTime(DateTimeUtils.toLocalDateTime(tripDTO.getDepartureDate(), tripDTO.getDepartureTime()));
        trip.setArrivalTime(DateTimeUtils.toLocalDateTime(tripDTO.getArrivalDate(), tripDTO.getArrivalTime()));
        trip.setFreeSeats(tripDTO.getFreeSeats());
        trip.setPrice(tripDTO.getPrice());
        trip.setStatus(TripStatus.NOT_COMPLETED);
        return trip;
    }

    public TripDTO toDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        tripDTO.setStartPoint(trip.getStartPoint());
        tripDTO.setFinalPoint(trip.getFinalPoint());
        tripDTO.setDepartureDate(DateTimeUtils.fromLocalDateTimeToStringDate(trip.getDepartureTime()));
        tripDTO.setDepartureTime(DateTimeUtils.fromLocalDateTimeToStringTime(trip.getDepartureTime()));
        tripDTO.setArrivalDate(DateTimeUtils.fromLocalDateTimeToStringDate(trip.getArrivalTime()));
        tripDTO.setArrivalTime(DateTimeUtils.fromLocalDateTimeToStringTime(trip.getArrivalTime()));
        tripDTO.setFreeSeats(trip.getFreeSeats());
        tripDTO.setPrice(trip.getPrice());
        tripDTO.setStatus(trip.getStatus());
        return tripDTO;
    }
}
