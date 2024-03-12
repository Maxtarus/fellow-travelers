package ru.sber.fellow_travelers.service;

import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.repository.RoleRepository;
import ru.sber.fellow_travelers.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository, RoleRepository roleRepository,
            JwtService jwtService, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signUp(UserDTO registerUser) {
        User user = new User();
        user.setEmail(registerUser.getEmail());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setPhoneNumber(registerUser.getPhoneNumber());
        user.setBirthDate(registerUser.getBirthDate());
        user.getRoles().add(roleRepository.findByType(RoleType.PASSENGER));

        if (registerUser.isDriver()) {
            user.getRoles().add(roleRepository.findByType(RoleType.DRIVER));
        }

        user = userRepository.save(user);

        return user;
    }

    @Transactional
    public User signIn(String username, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                var authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), password)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return user;
            }
        }

        return null;
    }

    public Cookie getAuthorizeCookie(User user) {
        String jwtToken = jwtService.generateTokenFromEmail(user.getUsername());
        return getCookie(jwtService.getHeaderName(), jwtToken, Math.toIntExact(jwtService.getExpiration() / 1000));
    }

    public Cookie getCookie(String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(expiry);
        return cookie;
    }

    public String getJwtHeaderName() {
        return jwtService.getHeaderName();
    }
}
