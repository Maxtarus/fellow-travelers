package ru.sber.fellow_travelers.dto;

import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Trip;

public class MarkTripDTO {
    private TripDTO trip;
    private Mark mark;

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public MarkTripDTO() { }

    public MarkTripDTO(TripDTO trip, Mark mark) {
        this.trip = trip;
        this.mark = mark;
    }
}
