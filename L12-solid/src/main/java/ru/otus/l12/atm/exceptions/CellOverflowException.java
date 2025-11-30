package ru.otus.l12.atm.exceptions;

public class CellOverflowException extends RuntimeException {
    public CellOverflowException(int nominal, int current, int capacity, int attempted) {
        super(String.format("Переполнение ячейки %dр: %d + %d > %d", nominal, current, attempted, capacity));
    }
}
