package me.domen.flightservice.controllers;

import lombok.RequiredArgsConstructor;
import me.domen.flightservice.services.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/flights")
    public String getFlightsByFilters(
            Model model,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "destination", required = false) String destination) {
        model.addAttribute("origins", flightService.getFlightOrigins());
        model.addAttribute("destinations", flightService.getFlightDestinations());
        if (origin != null && destination != null) {
            model.addAttribute("flightOrigin", origin);
            model.addAttribute("flightDestination", destination);
            model.addAttribute("flights", flightService.getFlightsByFilters(origin, destination));
        }
        return "flights";
    }
}
