package me.domen.flightservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.domen.flightservice.exceptions.InvalidLengthException;
import me.domen.flightservice.models.Flight;
import me.domen.flightservice.services.FlightService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class StartupHousekeeper {

    private final FlightService flightService;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        try {
            readASCIIAndPersist("data.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readASCIIAndPersist(String path) throws IOException, InvalidLengthException {
        List<Flight> flights = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (String line; (line = reader.readLine()) != null;) {
                String[] values = line.split("\\^");
                if (values.length != 9) throw new InvalidLengthException("Record length doesn't match database entity");
                flights.add(new Flight(values));
            }
        }
        flightService.saveAll(flights);
    }
}
