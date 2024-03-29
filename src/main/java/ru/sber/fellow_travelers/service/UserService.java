package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void deleteById(long id);
    User findById(long id);
    User findByEmail(String email);
    void save(User user);
}
