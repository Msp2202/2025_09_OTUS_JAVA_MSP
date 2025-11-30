package ru.otus.l12.atm.exceptions;

public class InsufficientNotesException extends RuntimeException {
    public InsufficientNotesException(String message, int nominal, int current, int attempted) {
        super(String.format(message, nominal, current, attempted));
    }
}
