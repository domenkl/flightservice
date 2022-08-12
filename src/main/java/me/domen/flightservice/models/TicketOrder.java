package me.domen.flightservice.models;

import lombok.Data;

@Data
public class TicketOrder {

    private String message;
    private boolean accepted;
    private String flightNumber;
    private int quantity;

    public TicketOrder(String message, boolean isAccepted, String flightNumber, int quantity) {
        this.message = message;
        this.accepted = isAccepted;
        this.flightNumber = flightNumber;
        this.quantity = quantity;
    }
}
