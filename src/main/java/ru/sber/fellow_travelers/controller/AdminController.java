package ru.sber.fellow_travelers.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.UserNotFoundException;
import ru.sber.fellow_travelers.mapper.UserMapper;
import ru.sber.fellow_travelers.service.AuthService;
import ru.sber.fellow_travelers.service.UserService;
import ru.sber.fellow_travelers.thymeleaf.Counter;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final AuthService authService;
    private final UserMapper userMapper;

    public AdminController(UserService userService, AuthService authService, UserMapper userMapper) {
        this.userService = userService;
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @GetMapping("/userList")
    public ModelAndView showAdminProfilePage() {
        ModelAndView view = new ModelAndView("admin/adminProfile");
        view.addObject("users", userService.findAll());
        view.addObject("counter", new Counter());
        return view;
    }

    @PostMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        userService.deleteById(id);
        response.sendRedirect("/admin");
    }

    @GetMapping("/addUser")
    public ModelAndView showAddNewUserPage() {
        ModelAndView view = new ModelAndView("admin/addUser");
        view.addObject("registerUser", new UserDTO());
        return view;
    }

    @PostMapping("/addUser")
    public Object addUser(@ModelAttribute("registerUser") @Valid UserDTO registerUser,
                          BindingResult result,
                          HttpServletResponse response) throws IOException {
        if (result.hasErrors()) {
            return "admin/addUser";
        }

        authService.signUp(registerUser);
        response.sendRedirect("/admin");
        return null;
    }

    @GetMapping("/editUser/{id}")
    public ModelAndView showEditUserPage(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("admin/editUser");

        try {
            User userEntity = userService.findById(id);
            UserDTO user = userMapper.toDTO(userEntity);
            view.addObject("userDTO", user);
            view.addObject("userId", userEntity.getId());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return view;
    }

    @PostMapping("/editUser/{id}")
    public Object editUser(@PathVariable("id") long id,
                           @ModelAttribute("userDTO") @Valid UserDTO user,
                           BindingResult result,
                           HttpServletResponse response) throws IOException {

        if (result.hasErrors()) {
            return "admin/editUser";
        }

        User userEntity = userMapper.toEntity(user);
        userEntity.setId(id);

        userService.save(userEntity);
        response.sendRedirect("/admin/userList");
        return null;
    }
}


