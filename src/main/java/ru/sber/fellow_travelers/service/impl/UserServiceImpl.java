package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.UserNotFoundException;
import ru.sber.fellow_travelers.repository.UserRepository;
import ru.sber.fellow_travelers.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id=" + id + " not found"));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
