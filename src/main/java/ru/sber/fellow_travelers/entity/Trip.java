package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_point", nullable = false)
    private String startPoint;
    @Column(name = "final_point", nullable = false)
    private String finalPoint;
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;
    @Column(name = "free_seats", nullable = false)
    private Integer freeSeats;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TripStatus status;
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private List<Request> requests = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private List<Mark> marks = new ArrayList<>();

    public Trip() { }

    public Trip(Long id, Integer freeSeats, TripStatus status, User driver) {
        this.id = id;
        this.freeSeats = freeSeats;
        this.status = status;
        this.driver = driver;
    }

    public Trip(Long id, String startPoint, String finalPoint, LocalDateTime departureTime,
                LocalDateTime arrivalTime, Integer freeSeats, Integer price,
                TripStatus status, User driver) {
        this.id = id;
        this.startPoint = startPoint;
        this.finalPoint = finalPoint;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.freeSeats = freeSeats;
        this.price = price;
        this.status = status;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getFinalPoint() {
        return finalPoint;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public Integer getPrice() {
        return price;
    }

    public TripStatus getStatus() {
        return status;
    }

    public User getDriver() {
        return driver;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setFinalPoint(String finalPoint) {
        this.finalPoint = finalPoint;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
