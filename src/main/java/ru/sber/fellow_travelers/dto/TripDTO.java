package ru.sber.fellow_travelers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sber.fellow_travelers.entity.enums.TripStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


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
}
