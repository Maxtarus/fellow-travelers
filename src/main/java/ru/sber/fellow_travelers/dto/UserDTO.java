package ru.sber.fellow_travelers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Введите email!")
    @Email(message = "Введите корректный email!")
    private String email;
    @NotBlank(message = "Введите пароль!")
    private String password;
    @NotBlank(message = "Введите имя!")
    private String firstName;
    @NotBlank(message = "Введите фамилию!")
    private String lastName;
    @NotBlank(message = "Введите номер телефона!")
    @Pattern(regexp = "^8\\d{10}$", message = "Введите корректный номер телефона!")
    private String phoneNumber;
    private LocalDate birthDate;
    private boolean isDriver;
    private boolean isAdmin;
}
