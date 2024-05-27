package ru.sber.fellow_travelers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeoMapController {

    @GetMapping("/showMap")
    public ModelAndView showMap(@RequestParam("startPointLng") double startPointLng,
                                @RequestParam("startPointLat") double startPointLat,
                                @RequestParam("finalPointLng") double finalPointLng,
                                @RequestParam("finalPointLat") double finalPointLat) {
        ModelAndView view = new ModelAndView("geomap");
        view.addObject("startPointLng", startPointLng);
        view.addObject("startPointLat", startPointLat);
        view.addObject("finalPointLng", finalPointLng);
        view.addObject("finalPointLat", finalPointLat);
        return view;
    }

}