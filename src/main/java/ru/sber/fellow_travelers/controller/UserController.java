package ru.sber.fellow_travelers.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.exception.UserNotFoundException;
import ru.sber.fellow_travelers.mapper.UserMapper;
import ru.sber.fellow_travelers.security.utils.AuthUtils;
import ru.sber.fellow_travelers.service.UserService;
import ru.sber.fellow_travelers.service.impl.UserServiceImpl;
import ru.sber.fellow_travelers.thymeleaf.Counter;

@Controller
//@RequestMapping("/admin")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/registeredUsers")
    public ModelAndView showAdminProfilePage() {
        ModelAndView view = new ModelAndView("admin/adminProfile");
        view.addObject("me", AuthUtils.getUserFromContext());
        view.addObject("users", userService.findAll());
        view.addObject("counter", new Counter());
        return view;
    }

    @PostMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/registeredUsers";
    }

    @GetMapping("/addUser")
    public ModelAndView showAddUserPage() {
        ModelAndView view = new ModelAndView("admin/addUser");
        view.addObject("user", new UserDTO());
        return view;
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") @Valid UserDTO userDTO,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addUser";
        }

        User user = userMapper.toEntity(userDTO);
        userService.save(user);
        return "redirect:/registeredUsers";
    }

    @GetMapping("/editUser/{id}")
    public ModelAndView showEditUserPage(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("admin/editUser");

        try {
            User userEntity = userService.findById(id);
            view.addObject("user", userMapper.toDTO(userEntity));
            view.addObject("userId", userEntity.getId());
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        return view;
    }

    @PostMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") long id,
                           @ModelAttribute("user") @Valid UserDTO userDTO,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editUser";
        }

        User user = userMapper.toEntity(userDTO);
        user.setId(id);
        userService.save(user);
        return "redirect:/registeredUsers";
    }
}


