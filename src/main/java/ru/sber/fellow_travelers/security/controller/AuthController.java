package ru.sber.fellow_travelers.security.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.Role;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.security.service.AuthService;
import ru.sber.fellow_travelers.security.service.JwtProvider;
import ru.sber.fellow_travelers.service.RoleService;
import ru.sber.fellow_travelers.service.impl.RoleServiceImpl;

import java.io.IOException;


@Controller
public class AuthController {
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

        User user = authService.signIn(userInfo.getUsername(), userInfo.getPassword());
        if (user == null) {
            result.rejectValue("username", "", "Неверный логин или пароль!");
            return "login";
        }

        session.setAttribute("user", user);
        response.addCookie(jwtProvider.getAuthorizeCookie(user));

        ModelAndView view = new ModelAndView("chooseRole");
        Role roleAdmin = roleService.findByType(RoleType.ADMIN);
        Role roleDriver = roleService.findByType(RoleType.DRIVER);
        Role rolePassenger = roleService.findByType(RoleType.PASSENGER);

        view.addObject("roleAdmin", roleAdmin);
        view.addObject("roleDriver", roleDriver);
        view.addObject("rolePassenger", rolePassenger);

        if (user.isDriver() || user.isAdmin()) {
            view.addObject("user", user);
        }

        if (user.isDriver()) {
            view.addObject("roleDriver", roleDriver);
        }

        if (user.isAdmin()) {
            view.addObject("roleAdmin", roleAdmin);
        }

        if (user.isDriver() || user.isAdmin()) {
            return view;
        } else {
            response.sendRedirect("/myRequests");
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
