package ru.sber.fellow_travelers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.util.LocalDateUtils;

public class UserDTO {
//    @NotBlank(message = "Введите email!")
//    @Email(message = "Введите корректный email!")
    private String email;
//    @NotBlank(message = "Введите пароль!")
    private String password;
//    @NotBlank(message = "Введите имя!")
    private String firstName;
//    @NotBlank(message = "Введите фамилию!")
    private String lastName;
//    @Pattern(regexp = "^8\\d{10}$", message = "Введите корректный номер телефона!")
    private String phoneNumber;
//    @Pattern(regexp = "^([0-2][0-9]|3[0-1]).(0[0-9]|1[0-2]).(19[0-9][0-9]|20[0-1][0-9])$",
//            message = "Дата рождения не соответствует формату \"дд.мм.гггг\"!")
    private String birthDate;
    private boolean isDriver;

    public UserDTO() { }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDate = LocalDateUtils.convertToInputFormat(user.getBirthDate());
        this.isDriver = user.isDriver();
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
}
