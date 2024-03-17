package ru.sber.fellow_travelers.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.repository.RoleRepository;
import ru.sber.fellow_travelers.util.DateTimeUtils;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserMapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBirthDate(userDTO.getBirthDate());
        user.getRoles().add(roleRepository.findByType(RoleType.PASSENGER));

        if (userDTO.isDriver()) {
            user.getRoles().add(roleRepository.findByType(RoleType.DRIVER));
        }

        if (userDTO.isAdmin()) {
            user.getRoles().add(roleRepository.findByType(RoleType.ADMIN));
        }

        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setBirthDate(DateTimeUtils.convertToInputFormat(user.getBirthDate()));
        userDTO.setDriver(user.isDriver());
        userDTO.setAdmin(user.isAdmin());
        return userDTO;
    }
}
