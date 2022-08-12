package me.domen.flightservice.services;

import lombok.RequiredArgsConstructor;
import me.domen.flightservice.models.Flight;
import me.domen.flightservice.models.TicketOrder;
import me.domen.flightservice.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repository;

    public List<Flight> getFlights() {
        return repository.findAll();
    }

    public List<Flight> getFlightsByFilters(String origin, String destination) {
        return repository.getFlightsByFilters(origin, destination);
    }

    public void saveAll(List<Flight> flights) {
        repository.saveAll(flights);
    }

    public List<String> getFlightOrigins() {
        return repository.getFlightOrigins();
    }

    public List<String> getFlightDestinations() {
        return repository.getFlightDestinations();
    }

    public TicketOrder tryOrderFlight(Long id, int quantity) {
        Flight flight = repository.findById(id).orElse(null);
        String message = "Could not find the flight with id: %d.".formatted(id);
        boolean isAccepted = false;

        if (flight == null) return new TicketOrder(message, false, "", 0);

        String flightNumber = flight.getFlightNumber();
        int availableSeats = flight.getAvailableSeats();
        if (availableSeats == 0) message = "Flight is full";
        else if (availableSeats < quantity) {
            message = "Could not order %d tickets. There are only %d empty seats.".formatted(quantity, availableSeats);
        } else {
            flight.setAvailableSeats(availableSeats - quantity);
            repository.save(flight);
            message = "Booked %d ticket(s) for flight from %s to %s.".formatted(quantity, flight.getOrigin(), flight.getDestination());
            isAccepted = true;
        }
        return new TicketOrder(message, isAccepted, flightNumber, quantity);
    }
}
