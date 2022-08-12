package me.domen.flightservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String carrier;
    private int price;
    private String flightDay;
    private LocalTime flightTime;
    private String duration;
    private int availableSeats;

    public Flight(String[] values) {
        flightNumber = values[0];
        origin = values[1];
        destination = values[2];
        carrier = values[3];
        price = Integer.parseInt(values[4]);
        flightDay = values[5];
        flightTime = setupTime(values[6]);
        duration = values[7];
        availableSeats = Integer.parseInt(values[8]);
    }

    private LocalTime setupTime(String value) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m");
        return LocalTime.parse(value, dtf);
    }
}
