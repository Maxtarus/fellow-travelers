package ru.sber.fellow_travelers.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.DriverDTO;
import ru.sber.fellow_travelers.dto.ReviewTripDTO;
import ru.sber.fellow_travelers.dto.TripDTO;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.Review;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.exception.TripNotFoundException;
import ru.sber.fellow_travelers.mapper.DriverMapper;
import ru.sber.fellow_travelers.mapper.TripMapper;
import ru.sber.fellow_travelers.mapper.UserMapper;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.RequestService;
import ru.sber.fellow_travelers.service.TripService;
import ru.sber.fellow_travelers.thymeleaf.Counter;
import ru.sber.fellow_travelers.util.DateTimeUtils;
import ru.sber.fellow_travelers.yandex_geocoder.service.YandexGeocoderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TripController {
    private static final Logger LOGGER = LogManager.getLogger(TripController.class);
    private final TripService tripService;
    private final RequestService requestService;
    private final YandexGeocoderService geoService;
    private final UserMapper userMapper;
    private final DriverMapper driverMapper;
    private final TripMapper tripMapper;

    public TripController(TripService tripService,
                          RequestService requestService,
                          YandexGeocoderService geoService, UserMapper userMapper, DriverMapper driverMapper, TripMapper tripMapper) {
        this.tripService = tripService;
        this.requestService = requestService;
        this.geoService = geoService;
        this.userMapper = userMapper;
        this.driverMapper = driverMapper;
        this.tripMapper = tripMapper;
    }

    @GetMapping("/createdTrips")
    public ModelAndView showCreatedTrips() {
        ModelAndView view = new ModelAndView("driver/createdTrips");
        User user = AuthUtils.getUserFromContext();

        List<Trip> trips = tripService.findAllNotCompletedByDriverId(user.getId());
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip trip : trips) {
            TripDTO tripDTO = tripMapper.toDTO(trip);
            tripDTOs.add(tripDTO);
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
            TripDTO tripDTO = tripMapper.toDTO(trip);

            tripDTO.setStartPointCoordinates(geoService.getPointCoordinates(tripDTO.getStartPoint()));
            tripDTO.setFinalPointCoordinates(geoService.getPointCoordinates(tripDTO.getFinalPoint()));

            tripDTOs.add(tripDTO);
        }

        view.addObject("user", user);
        view.addObject("trips", tripDTOs);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/driverTripsHistory/passengers")
    public ModelAndView showPassengersForTrip(@RequestParam("tripId") long id) {
        ModelAndView view = new ModelAndView("driver/tripPassengers");
        List<Request> allApprovedRequestsForTrip = requestService.findAllApprovedForCompletedTripByTripId(id);
        Map<UserDTO, MarkType> marksByPassengers = new HashMap<>();

        for (Request request : allApprovedRequestsForTrip) {
            User passenger = request.getPassenger();
            List<Review> marksFromPassenger = passenger.getMarksFromUsers();

            if (marksFromPassenger.isEmpty()) {
                marksByPassengers.put(userMapper.toDTO(passenger), null);
            }

            for (Review review : marksFromPassenger) {
                if (review.getTrip().getId() == id) {
                    marksByPassengers.put(userMapper.toDTO(passenger), review.getMarkType());
                }
            }
        }

        view.addObject("marksByPassengers", marksByPassengers);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/passengerTripsHistory")
    public ModelAndView showPassengerTripsHistory() {
        ModelAndView view = new ModelAndView("passenger/tripsHistory");
        User user = AuthUtils.getUserFromContext();

        List<Request> approvedRequestsForCompletedTrips = requestService.findAllApprovedForCompletedTripsByPassenger(user);
        List<ReviewTripDTO> reviewTripDTOS = new ArrayList<>();

        for (Request request : approvedRequestsForCompletedTrips) {
            TripDTO tripDTO = tripMapper.toDTO(request.getTrip());

            tripDTO.setStartPointCoordinates(geoService.getPointCoordinates(tripDTO.getStartPoint()));
            tripDTO.setFinalPointCoordinates(geoService.getPointCoordinates(tripDTO.getFinalPoint()));

            if (!request.getTrip().getReviews().isEmpty()) {

                if (request.getTrip().getReviews()
                        .stream()
                        .anyMatch(m -> m.getFromUser().equals(user))) {
                    Review review = request.getTrip().getReviews().stream().filter(m -> m.getFromUser().equals(user)).findAny().get();
                    ReviewTripDTO reviewTrip = new ReviewTripDTO(tripDTO, review);
                    reviewTripDTOS.add(reviewTrip);
                } else {
                    reviewTripDTOS.add(new ReviewTripDTO(tripDTO, new Review()));
                }
            } else {
                reviewTripDTOS.add(new ReviewTripDTO(tripDTO, new Review()));
            }
        }

        view.addObject("reviewTrip", new ReviewTripDTO());
        view.addObject("reviewTripDTOS", reviewTripDTOS);
        view.addObject("user", user);
        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/driverInfo")
    public ModelAndView showDriverInfo(@RequestParam("tripId") long id) {
        ModelAndView view = new ModelAndView("passenger/driverInfo");

        try {
            Trip trip = tripService.findById(id);
            User driver = trip.getDriver();
            DriverDTO driverDTO = driverMapper.toDTO(driver);
            view.addObject("driver", driverDTO);
        } catch (TripNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        view.addObject("counter", new Counter());
        return view;
    }

    @GetMapping("/searchTrips")
    public ModelAndView showPassengerProfile() {
        return new ModelAndView("passenger/searchTrip");
    }

    @GetMapping("/foundTrips")
    public Object showFoundTrips(@RequestParam("startPoint") String startPoint,
                               @RequestParam("finalPoint") String finalPoint,
                               @RequestParam("departureDate") LocalDate departureDate,
                               @RequestParam("passengersNumber") int passengersNumber) {

        if (startPoint != null && finalPoint != null) {
            User user = AuthUtils.getUserFromContext();
            List<Trip> foundedTrips = tripService.findAllAvailableForPassenger(user, passengersNumber, startPoint, finalPoint, departureDate);
            List<TripDTO> tripDTOs = new ArrayList<>();

            for (Trip trip : foundedTrips) {
                tripDTOs.add(tripMapper.toDTO(trip));
            }

            ModelAndView view = new ModelAndView("passenger/foundTrips");
            view.addObject("trips", tripDTOs);
            view.addObject("startPoint", startPoint);
            view.addObject("finalPoint", finalPoint);
            view.addObject("departureDate", departureDate);
            view.addObject("passengersNumber", passengersNumber);
            view.addObject("user", user);
            view.addObject("counter", new Counter());

            return view;
        } else {
            return "redirect:/searchTrips";
        }
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
    public ModelAndView showEditTripPage(@PathVariable("id") long id) {
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
    public String editTrip(@PathVariable("id") long id,
                           @ModelAttribute("trip") @Valid TripDTO tripDTO,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "driver/editTrip";
        }

        Trip trip = tripMapper.toEntity(tripDTO);
        trip.setId(id);
        User driver = AuthUtils.getUserFromContext();
        trip.setDriver(driver);
        trip.setTripStatus(TripStatus.NOT_COMPLETED);
        tripService.save(trip);
        return "redirect:/createdTrips";
    }

}
