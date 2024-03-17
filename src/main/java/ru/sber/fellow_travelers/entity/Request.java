package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.enums.RequestStatus;

import java.util.Objects;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Request() { }

    public Request(RequestStatus status, User passenger, Trip trip) {
        this.status = status;
        this.passenger = passenger;
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public User getPassenger() {
        return passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
