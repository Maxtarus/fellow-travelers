package ru.sber.fellow_travelers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driver")
public class DriverController {
    @GetMapping
    public String showDriverProfile() {
        return "driver/driverProfile";
    }
}
