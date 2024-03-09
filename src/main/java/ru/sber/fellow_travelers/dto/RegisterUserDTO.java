package ru.sber.fellow_travelers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.format.DateTimeFormatter;

public class RegisterUserDTO {
    @NotBlank(message = "Введите email!")
    @Email(message = "Введите корректный email!")
    private String email;
    @NotBlank(message = "Введите пароль!")
    private String password;
    @NotBlank(message = "Введите имя!")
    private String firstName;
    @NotBlank(message = "Введите фамилию!")
    private String lastName;
    @Pattern(regexp = "^8\\d{10}$", message = "Введите корректный номер телефона!")
    private String phoneNumber;
    @Pattern(regexp = "^([0-2][0-9]|3[0-1]).(0[0-9]|1[0-2]).(19[0-9][0-9]|20[0-1][0-9])$",
            message = "Дата рождения не соответствует формату \"дд.мм.гггг\"!")
    private String birthDate;
    private boolean isDriver;

    public RegisterUserDTO() { }

    public RegisterUserDTO(String email, String password, String firstName, String lastName,
                           String phoneNumber, String birthDate, boolean isDriver) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.isDriver = isDriver;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }
}
