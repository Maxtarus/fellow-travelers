package ru.sber.fellow_travelers.configuration;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.entity.Role;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.repository.RoleRepository;
import ru.sber.fellow_travelers.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        roleRepository.saveAll(
                List.of(
                        new Role(1L, RoleType.ADMIN),
                        new Role(2L, RoleType.DRIVER),
                        new Role(3L, RoleType.PASSENGER)
                )
        );

        userRepository.save(
                new User(1L, "admin@mail.ru",
                        passwordEncoder.encode("admin"),
                        "Admin", "Admin", "89997775544", "2000-01-01",
                        Set.of(getRoles().get(0)))
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
