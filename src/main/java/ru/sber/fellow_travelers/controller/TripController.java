package ru.sber.fellow_travelers.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.MarkTripDTO;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.mapper.TripMapper;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.MarkService;
import ru.sber.fellow_travelers.service.RequestService;
import ru.sber.fellow_travelers.service.TripService;
import ru.sber.fellow_travelers.service.impl.MarkServiceImpl;
import ru.sber.fellow_travelers.service.impl.RequestServiceImpl;
import ru.sber.fellow_travelers.thymeleaf.Counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TripController {
    private static final Logger LOGGER = LogManager.getLogger(TripController.class);
    private final TripService tripService;
    private final RequestService requestService;
    private final MarkService markService;
    private final TripMapper tripMapper;

    public TripController(TripService tripService,
                          RequestServiceImpl requestService,
                          MarkServiceImpl feedbackService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.requestService = requestService;
        this.markService = feedbackService;
        this.tripMapper = tripMapper;
    }

    @GetMapping("/createdTrips")
    public ModelAndView showCreatedTrips() {
        ModelAndView view = new ModelAndView("driver/createdTrips");
        User user = AuthUtils.getUserFromContext();

        List<Trip> trips = tripService.findAllNotCompletedByDriverId(user.getId());
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip trip : trips) {
            tripDTOs.add(tripMapper.toDTO(trip));
        }

        view.addObject("user", user);
        view.addObject("trips", tripDTOs);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/driverTripsHistory")
    public ModelAndView showDriverTripsHistory() {
        ModelAndView view = new ModelAndView("driver/tripsHistory");
        User user = AuthUtils.getUserFromContext();

        List<Trip> trips = tripService.findAllCompletedByDriverId(user.getId());
        List<TripDTO> tripDTOs = new ArrayList<>();

        for (Trip trip : trips) {
            tripDTOs.add(tripMapper.toDTO(trip));
        }

        view.addObject("user", user);
        view.addObject("trips", tripDTOs);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/passengerTripsHistory")
    public ModelAndView showPassengerTripsHistory() {
        ModelAndView view = new ModelAndView("passenger/tripsHistory");
        User user = AuthUtils.getUserFromContext();

        List<Request> approvedRequestsForCompletedTrips = requestService.findAllApprovedForCompletedTrips(user);
        List<MarkTripDTO> markTripDTOs = new ArrayList<>();

        for (Request request : approvedRequestsForCompletedTrips) {
            TripDTO tripDTO = tripMapper.toDTO(request.getTrip());
            if (!request.getTrip().getMarks().isEmpty()) {

                if (request.getTrip().getMarks()
                        .stream()
                        .anyMatch(m -> m.getFromUser().equals(user))) {
                    Mark mark = request.getTrip().getMarks().stream().filter(m -> m.getFromUser().equals(user)).findAny().get();
                    MarkTripDTO markTrip = new MarkTripDTO(tripDTO, mark);
                    markTripDTOs.add(markTrip);
//                    view.addObject(markTrip);
                } else {
                    Mark mark = new Mark();
                    MarkTripDTO markTrip = new MarkTripDTO(tripDTO, mark);
                    markTripDTOs.add(markTrip);
//                    view.addObject(markTrip);
                }
            } else {
                Mark mark = new Mark();
                MarkTripDTO markTrip = new MarkTripDTO(tripDTO, mark);
                markTripDTOs.add(markTrip);
//                view.addObject(markTrip);
            }
        }

        view.addObject("markTrip", new MarkTripDTO());
        view.addObject("markTripDTOs", markTripDTOs);
        view.addObject("user", user);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/availableTrips")
    public ModelAndView showPassengerProfile() {
        ModelAndView view = new ModelAndView("passenger/availableTrips");
        User user = AuthUtils.getUserFromContext();

        List<Trip> trips = tripService.findAllAvailableForPassenger(user);
        List<TripDTO> tripDTOs = new ArrayList<>();

        for (Trip trip : trips) {
            tripDTOs.add(tripMapper.toDTO(trip));
        }

        view.addObject("user", user);
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
        return "redirect:/createdTrips";
    }

    @PostMapping("deleteTrip/{id}")
    public String deleteTrip(@PathVariable("id") long id) {
        tripService.deleteById(id);
        return "redirect:/createdTrips";
    }

    @PostMapping("completeTrip/{id}")
    public String completeTrip(@PathVariable("id") long id) {
        try {
            Trip trip = tripService.findById(id);
            tripService.completeTrip(trip);
        } catch (TripNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return "redirect:/createdTrips";
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
        return "redirect:/createdTrips";
    }

}
