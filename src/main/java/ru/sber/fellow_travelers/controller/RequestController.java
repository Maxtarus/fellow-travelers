package ru.sber.fellow_travelers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RequestStatus;
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
    private final TripService tripService;
    private final RequestService requestService;
    private final TripMapper tripMapper;

    public RequestController(TripServiceImpl tripService, RequestServiceImpl requestService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.requestService = requestService;
        this.tripMapper = tripMapper;
    }

    @GetMapping("/myRequests")
    public ModelAndView showMyRequests() {
        ModelAndView view = new ModelAndView("passenger/passengerProfile");
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


    @PostMapping("/bookPlace/{id}")
    public String bookPlace(@PathVariable("id") long id) {
        Trip trip = tripService.findById(id);
        User user = AuthUtils.getUserFromContext();
        Request request = new Request(RequestStatus.UNDER_CONSIDERATION, user, trip);
        requestService.save(request);
        return "redirect:/myRequests";
    }

    @PostMapping("deleteRequest/{id}")
    public String deleteRequest(@PathVariable("id") long id) {
        requestService.deleteById(id);
        return "redirect:/myRequests";
    }

}
