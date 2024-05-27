package ru.sber.fellow_travelers.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.entity.Review;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.ReviewService;
import ru.sber.fellow_travelers.service.TripService;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final TripService tripService;
    private final ReviewService reviewService;

    @GetMapping("/leaveReview")
    public ModelAndView showLeaveCommentPage(@RequestParam("reviewId") long reviewId) {
        ModelAndView view = new ModelAndView("review");
        Review review = reviewService.findById(reviewId);
        view.addObject("review", review);
        return view;
    }

    @PostMapping("/leaveReview")
    public String leaveReview(@ModelAttribute("review") Review review,
                              @RequestParam("tripId") long tripId,
                              @RequestParam("markType") MarkType markType
    ) {
        Trip trip = tripService.findById(tripId);
        User toUser = trip.getDriver();
        User fromUser = AuthUtils.getUserFromContext();
        reviewService.rateAfterTrip(toUser, fromUser, trip, review, markType);
        return "redirect:/passengerTripsHistory";
    }
}
