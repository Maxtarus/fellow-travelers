package ru.sber.fellow_travelers.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.RequestNotFoundException;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.mapper.TripMapper;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.RequestService;
import ru.sber.fellow_travelers.service.TripService;
import ru.sber.fellow_travelers.service.impl.RequestServiceImpl;
import ru.sber.fellow_travelers.service.impl.TripServiceImpl;
import ru.sber.fellow_travelers.thymeleaf.Counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {
    private static final Logger LOGGER = LogManager.getLogger(RequestController.class);
    private final TripService tripService;
    private final RequestService requestService;
    private final TripMapper tripMapper;

    public RequestController(TripServiceImpl tripService, RequestServiceImpl requestService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.requestService = requestService;
        this.tripMapper = tripMapper;
    }

    @GetMapping("/activeRequests")
    public ModelAndView showActiveRequests() {
        ModelAndView view = new ModelAndView("passenger/activeRequests");
        User user = AuthUtils.getUserFromContext();
        view.addObject("user", user);
        List<Request> requests = requestService.findAllByPassengerId(user.getId());

        Map<Request, TripDTO> tripsByRequests = new HashMap<>();
        for (Request request : requests) {
            tripsByRequests.put(request, tripMapper.toDTO(request.getTrip()));
        }

        view.addObject("tripsByRequests", tripsByRequests);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/passengersRequests")
    public ModelAndView showPassengersRequests() {
        ModelAndView view = new ModelAndView("driver/passengersRequests");
        User user = AuthUtils.getUserFromContext();
        view.addObject("user", user);
        List<Request> requests = requestService.findAvailableByDriverId(user.getId());

        Map<Request, TripDTO> tripsByRequests = new HashMap<>();
        for (Request request : requests) {
            tripsByRequests.put(request, tripMapper.toDTO(request.getTrip()));
        }

        view.addObject("tripsByRequests", tripsByRequests);
        view.addObject("counter", new Counter());
        return view;
    }

    @PostMapping("/createRequest/{id}")
    public String createRequest(@PathVariable("id") long id) {
        try {
            Trip trip = tripService.findById(id);
            User user = AuthUtils.getUserFromContext();
            requestService.createRequest(user, trip);
        } catch (TripNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return "redirect:/activeRequests";
    }

    @PostMapping("/approveRequest/{id}")
    public String approveRequest(@PathVariable("id") long id) {
        try {
            Request request = requestService.findById(id);
            requestService.approveRequest(request);
            tripService.decrementFreeSeats(request.getTrip());
        } catch (RequestNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return "redirect:/passengersRequests";
    }

    @PostMapping("/disapproveRequest/{id}")
    public String disapproveRequest(@PathVariable("id") long id) {
        try {
            Request request = requestService.findById(id);
            requestService.disapproveRequest(request);
        } catch (RequestNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return "redirect:/passengersRequests";
    }

    @PostMapping("deleteRequest/{id}")
    public String deleteRequest(@PathVariable("id") long id) {
        requestService.deleteById(id);
        return "redirect:/activeRequests";
    }

}
