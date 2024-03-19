package ru.sber.fellow_travelers.configuration;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.entity.Role;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RequestStatus;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.repository.RequestRepository;
import ru.sber.fellow_travelers.repository.TripRepository;
import ru.sber.fellow_travelers.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final RequestRepository requestRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationInitializer(UserRepository userRepository, TripRepository tripRepository, RequestRepository requestRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.requestRepository = requestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        userRepository.saveAll(
                List.of(
                        new User(1L, "admin@mail.ru",
                            passwordEncoder.encode("admin"),
                            "Николай", "Касперский", "89997775544", "1998-08-21",
                            Set.of(getRoles().get(0))),
                        new User(2L, "starmax@yandex.ru",
                                passwordEncoder.encode("1"),
                                "Максим", "Старостин", "89008887733", "2001-11-11",
                                Set.of(getRoles().get(0), getRoles().get(1), getRoles().get(2))),
                        new User(3L, "imosolov@gmail.com",
                                passwordEncoder.encode("zaq1"),
                                "Иван", "Мосолов", "89886541832", "2000-09-02",
                                Set.of(getRoles().get(1), getRoles().get(2))),
                        new User(4L, "www@gmail.com",
                                passwordEncoder.encode("zaq1"),
                                "Владислав", "Даванков", "89886541832", "2000-09-02",
                                Set.of(getRoles().get(1)))
                )
        );

        tripRepository.saveAll(
                List.of(
                        new Trip(1L, "Казань", "Новосибирск",
                                LocalDateTime.of(2024, 3, 16, 7, 30),
                                LocalDateTime.of(2024, 3, 16, 9, 50),
                                2, 1500, TripStatus.NOT_COMPLETED, userRepository.findById(2L).get()),
                        new Trip(2L, "Москва", "Липецк",
                                LocalDateTime.of(2024, 3, 16, 16, 30),
                                LocalDateTime.of(2024, 3, 16, 19, 0),
                                1, 1500, TripStatus.NOT_COMPLETED, userRepository.findById(2L).get()),
                        new Trip(3L, "Касимов", "Рязань",
                                LocalDateTime.of(2024, 4, 18, 18, 30),
                                LocalDateTime.of(2024, 4, 16, 21, 0),
                                3, 3000, TripStatus.COMPLETED, userRepository.findById(3L).get())

                )
        );

        requestRepository.saveAll(
                List.of(
                        new Request(1L, RequestStatus.UNDER_CONSIDERATION, userRepository.findById(3L).get(),
                                tripRepository.findById(1L).get()),
                        new Request(2L, RequestStatus.UNDER_CONSIDERATION, userRepository.findById(3L).get(),
                                tripRepository.findById(2L).get())
                )
        );

    }

    private List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, RoleType.ADMIN));
        roles.add(new Role(2L, RoleType.PASSENGER));
        roles.add(new Role(3L, RoleType.DRIVER));
        return roles;
    }
}
