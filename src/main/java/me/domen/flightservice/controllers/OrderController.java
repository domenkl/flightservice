package me.domen.flightservice.controllers;

import lombok.RequiredArgsConstructor;
import me.domen.flightservice.services.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final FlightService flightService;

    @PostMapping("/order")
    public String acceptOrder(Model model, @RequestParam(name = "id") Long id, @RequestParam(name = "quantity") int quantity) {
        model.addAttribute("orderMessage", flightService.tryOrderFlight(id, quantity));
        return "order";
    }

    /*@GetMapping("/order")
    public String testOrder(@RequestParam(name = "id") Long id, @RequestParam(name = "quantity") int quantity) {
        return flightService.tryOrderFlight(id, quantity);
    }*/

    /*@PostMapping("/order")
    public ModelAndView acceptOrder(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        Long id = Long.valueOf(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        mv.addObject("orderMessage", flightService.tryOrderFlight(id, quantity));
        mv.setViewName("order");
        return mv;
    }*/
}
