package me.domen.flightservice.controllers;

import lombok.RequiredArgsConstructor;
import me.domen.flightservice.services.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final FlightService flightService;

    @PostMapping("/order")
    public String acceptOrder(Model model, @RequestParam(name = "id") Long id, @RequestParam(name = "quantity") int quantity) {
        model.addAttribute("order", flightService.tryOrderFlight(id, quantity));
        return "order";
    }
}
