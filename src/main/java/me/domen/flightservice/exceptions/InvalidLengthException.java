package me.domen.flightservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidLengthException extends Exception{

    public InvalidLengthException(String description) {
        super(description);
    }
}
