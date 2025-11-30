package ru.otus.l12.atm.exceptions;

public class WithdrawalException extends RuntimeException {
    public WithdrawalException(String message) {
        super(message);
    }
}
