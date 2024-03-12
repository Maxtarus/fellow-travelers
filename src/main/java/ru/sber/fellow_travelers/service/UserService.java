package ru.sber.fellow_travelers.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.UserNotFoundException;
import ru.sber.fellow_travelers.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id=" + id + "not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
