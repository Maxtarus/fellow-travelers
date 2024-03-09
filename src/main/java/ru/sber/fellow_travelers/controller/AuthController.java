package ru.sber.fellow_travelers.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.RegisterUserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.service.AuthService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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
        response.addCookie(authService.getAuthorizeCookie(user));

        if (user.isAdmin()) {
            response.sendRedirect("/admin");
        } else if (user.isDriver()) {
            response.sendRedirect("/driver");
        } else {
            response.sendRedirect("/passenger");
        }

        return null;
    }

    @GetMapping("/signUp")
    public ModelAndView registrationPage() {
        ModelAndView view = new ModelAndView("registration");
        view.addObject("registerUser", new RegisterUserDTO());
        return view;
    }

    @PostMapping("/signUp")
    public Object signUp(
            @ModelAttribute("registerUser") @Valid RegisterUserDTO registerUser,
            BindingResult result,
            HttpServletResponse response,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "registration";
        }

        User user = authService.signUp(registerUser);
        session.setAttribute("user", user);

        response.addCookie(authService.getAuthorizeCookie(user));
        response.sendRedirect("/");
        return null;
    }

    @GetMapping("/logout")
    public void logout(
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session
    ) throws IOException {
        Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies())
                .filter(c -> authService.getJwtHeaderName().equals(c.getName()))
                .findAny();
        if (cookieOptional.isPresent()) {
            Cookie cookie = authService.getCookie(authService.getJwtHeaderName(), null, 0);
            response.addCookie(cookie);
            session.invalidate();
            response.sendRedirect("/");
        }
    }
}
