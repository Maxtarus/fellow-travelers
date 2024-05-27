package ru.sber.fellow_travelers.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
public class DriverDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String averageMark;
    private long completedTripsAmount;
    private List<String> comments;
}
