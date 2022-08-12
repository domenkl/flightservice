package me.domen.flightservice.services;

import lombok.RequiredArgsConstructor;
import me.domen.flightservice.models.Flight;
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

    public String tryOrderFlight(Long id, int quantity) {
        Flight flight = repository.findById(id).orElse(null);
        if (flight == null) return "Could not find the flight with id: %d.".formatted(id);

        int availableSeats = flight.getAvailableSeats();
        if (availableSeats >= quantity) {
            flight.setAvailableSeats(availableSeats - quantity);
            repository.save(flight);
            return "Booked %d tickets for flights from %s to %s".formatted(quantity, flight.getOrigin(), flight.getDestination());
        }
        if (availableSeats == 0) return "Flight is full.";
        return "Could not order %d tickets. There are only %d empty seats.".formatted(quantity, availableSeats);
    }
}
