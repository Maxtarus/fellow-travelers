package ru.sber.fellow_travelers.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sber.fellow_travelers.dto.MarkTripDTO;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.mapper.TripMapper;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.MarkService;
import ru.sber.fellow_travelers.service.TripService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MarkController {
    private static final Logger LOGGER = LogManager.getLogger(MarkController.class);
    private final TripService tripService;
    private final MarkService markService;

    public MarkController(TripService tripService, MarkService markService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.markService = markService;
    }

    @PostMapping("/passengerTripsHistory/rateDriver/{id}")
    public String rateDriver(@PathVariable("id") long id,
                             @ModelAttribute("markTrip") MarkTripDTO markTrip
    ) {
        try {
            Trip trip = tripService.findById(id);
            User toUser = trip.getDriver();
            User fromUser = AuthUtils.getUserFromContext();
            Mark mark =  markTrip.getMark();
            markService.rateAfterTrip(toUser, fromUser, trip, mark);
        } catch (TripNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return "redirect:/passengerTripsHistory";
    }
}
