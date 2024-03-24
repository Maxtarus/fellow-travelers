package ru.sber.fellow_travelers.dto;

public class DriverDTO {
    private String firstName;
    private String lastName;
    private String birthDate;
    private double averageMark;
    private long completedTripsAmount;

    public DriverDTO() { }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public long getCompletedTripsAmount() {
        return completedTripsAmount;
    }

    public void setCompletedTripsAmount(long completedTripsAmount) {
        this.completedTripsAmount = completedTripsAmount;
    }
}
