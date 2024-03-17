package ru.sber.fellow_travelers.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.mapper.TripMapper;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.TripService;
import ru.sber.fellow_travelers.service.impl.TripServiceImpl;
import ru.sber.fellow_travelers.thymeleaf.Counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
public class TripController {
    private static final Logger LOGGER = LogManager.getLogger(TripController.class);
    private final TripService tripService;
    private final TripMapper tripMapper;

    public TripController(TripServiceImpl tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }

    @GetMapping("/myTrips")
    public ModelAndView showDriverProfile() {
        ModelAndView view = new ModelAndView("driver/driverProfile");
        User user = AuthUtils.getUserFromContext();
        view.addObject("user", user);

        List<Trip> trips = tripService.findAllByDriverId(user.getId());
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip trip : trips) {
            tripDTOs.add(tripMapper.toDTO(trip));
        }

        view.addObject("trips", tripDTOs);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/availableTrips")
    public ModelAndView showPassengerProfile() {
        ModelAndView view = new ModelAndView("passenger/availableTrips");
        User user = AuthUtils.getUserFromContext();
        view.addObject("user", user);

//        List<Trip> trips;
//        try {
//            trips = tripService.findAllAvailableForPassenger(user.getId());
//        } catch (IncorrectResultSizeDataAccessException e) {
//            trips = Collections.emptyList();
//        }

        List<Trip> trips = tripService.findAllAvailableForPassenger(user.getId());
        List<TripDTO> tripDTOs = new ArrayList<>();

        for (Trip trip : trips) {
            tripDTOs.add(tripMapper.toDTO(trip));
        }

        view.addObject("trips", tripDTOs);

        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/createTrip")
    public ModelAndView showCreateTripPage() {
        ModelAndView view = new ModelAndView("driver/createTrip");
        view.addObject("trip", new TripDTO());
        return view;
    }

    @PostMapping("/createTrip")
    public String createTrip(@ModelAttribute("trip") @Valid TripDTO tripDTO,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "driver/createTrip";
        }

        Trip trip = tripMapper.toEntity(tripDTO);
        User driver = AuthUtils.getUserFromContext();
        trip.setDriver(driver);
        tripService.save(trip);
        return "redirect:/myTrips";
    }

    @PostMapping("deleteTrip/{id}")
    public String deleteTrip(@PathVariable("id") long id) {
        tripService.deleteById(id);
        return "redirect:/myTrips";
    }

    @GetMapping("/editTrip/{id}")
    public ModelAndView showEditUserPage(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("driver/editTrip");

        try {
            Trip tripEntity = tripService.findById(id);
            view.addObject("trip", tripMapper.toDTO(tripEntity));
        } catch (TripNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return view;
    }

    @PostMapping("/editTrip/{id}")
    public String editUser(@PathVariable("id") long id,
                           @ModelAttribute("trip") @Valid TripDTO tripDTO,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "driver/editTrip";
        }

        Trip trip = tripMapper.toEntity(tripDTO);
        trip.setId(id);
        User driver = AuthUtils.getUserFromContext();
        trip.setDriver(driver);
        tripService.save(trip);
        return "redirect:/myTrips";
    }

}
