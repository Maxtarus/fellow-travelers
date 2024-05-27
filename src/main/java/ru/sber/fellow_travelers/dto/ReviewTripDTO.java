package ru.sber.fellow_travelers.dto;

import ru.sber.fellow_travelers.entity.Review;


public class ReviewTripDTO {
    private TripDTO trip;
    private Review review;

    public ReviewTripDTO() { }

    public ReviewTripDTO(TripDTO trip, Review review) {
        this.trip = trip;
        this.review = review;
    }


    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

}
