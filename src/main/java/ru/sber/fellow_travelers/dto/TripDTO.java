package ru.sber.fellow_travelers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.google_maps_api.response.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class TripDTO {
    private long id;
    @NotBlank(message = "Введите начальную точку!")
    private String startPoint;
    @NotBlank(message = "Введите конечную точку!")
    private String finalPoint;
    @NotBlank(message = "Введите дату отправления!")
    @Pattern(regexp = "^([0-2][0-9]|3[0-1]).(0[1-9]|1[0-2]).(2024)$",
            message = "Дата отправления не соответствует формату \"дд.мм.гггг\"!")
    private String departureDate;
    @NotBlank(message = "Введите время отправления!")
    private String departureTime;
    @NotBlank(message = "Введите дату прибытия!")
    @Pattern(regexp = "^([0-2][0-9]|3[0-1]).(0[1-9]|1[0-2]).(2024)$",
            message = "Дата прибытия не соответствует формату \"дд.мм.гггг\"!")
    private String arrivalDate;
    @NotBlank(message = "Введите время прибытия!")
    private String arrivalTime;
    private int freeSeats;
    private int price;
    private TripStatus status;
    private UserDTO driver;
    private Coordinates startPointCoordinates;
    private Coordinates finalPointCoordinates;
    private List<Mark> marks = new ArrayList<>();

    public TripDTO() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(String finalPoint) {
        this.finalPoint = finalPoint;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public UserDTO getDriver() {
        return driver;
    }

    public void setDriver(UserDTO driver) {
        this.driver = driver;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Coordinates getStartPointCoordinates() {
        return startPointCoordinates;
    }

    public void setStartPointCoordinates(Coordinates startPointCoordinates) {
        this.startPointCoordinates = startPointCoordinates;
    }

    public Coordinates getFinalPointCoordinates() {
        return finalPointCoordinates;
    }

    public void setFinalPointCoordinates(Coordinates finalPointCoordinates) {
        this.finalPointCoordinates = finalPointCoordinates;
    }
}
