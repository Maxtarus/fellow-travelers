package ru.sber.fellow_travelers.security.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.exception.UserNotFoundException;
import ru.sber.fellow_travelers.security.service.AuthService;
import ru.sber.fellow_travelers.security.service.JwtProvider;
import ru.sber.fellow_travelers.service.RoleService;
import ru.sber.fellow_travelers.service.impl.RoleServiceImpl;

import java.io.IOException;


@Controller
public class AuthController {
    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);
    private final AuthService authService;
    private final JwtProvider jwtProvider;
    private final RoleService roleService;

    public AuthController(AuthService authService, JwtProvider jwtProvider, RoleServiceImpl roleService) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView("login");
        view.addObject("user", new User());
        return view;
    }

    @PostMapping("/signIn")
    public Object signIn(
            @ModelAttribute @Valid User userInfo,
            BindingResult result,
            HttpServletResponse response,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "login";
        }

        User user;
        try {
            user = authService.signIn(userInfo.getUsername(), userInfo.getPassword());
        } catch (AuthenticationException | UserNotFoundException e) {
            LOGGER.error(e.getMessage());
            result.rejectValue("username", "", "Неверный логин или пароль!");
            return "login";
        }

        session.setAttribute("user", user);
        response.addCookie(jwtProvider.getAuthorizeCookie(user));

        if (user.getRoles().size() > 1) {
            ModelAndView view = new ModelAndView("chooseRole");
            view.addObject("user", user);
            view.addObject("roleAdmin", roleService.findByType(RoleType.ADMIN));
            view.addObject("roleDriver", roleService.findByType(RoleType.DRIVER));
            view.addObject("rolePassenger", roleService.findByType(RoleType.PASSENGER));
            return view;
        } else if (user.isAdmin()) {
            response.sendRedirect("/registeredUsers");
        } else if (user.isDriver()) {
            response.sendRedirect("/createdTrips");
        } else {
            response.sendRedirect("/activeRequests");
        }

        return null;
    }

    @GetMapping("/signUp")
    public ModelAndView registrationPage() {
        ModelAndView view = new ModelAndView("registration");
        view.addObject("registerUser", new UserDTO());
        return view;
    }

    @PostMapping("/signUp")
    public Object signUp(
            @ModelAttribute("registerUser") @Valid UserDTO registerUser,
            BindingResult result,
            HttpServletResponse response,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "registration";
        }

        User user = authService.signUp(registerUser);
        session.setAttribute("user", user);

        response.addCookie(jwtProvider.getAuthorizeCookie(user));
        response.sendRedirect("/");
        return null;
    }
}
