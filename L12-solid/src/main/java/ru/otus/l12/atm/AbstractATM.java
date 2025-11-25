package ru.otus.l12.atm;

import ru.otus.l12.atm.interfaces.Balance;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractATM implements Balance {
    protected final Map<Integer, Cell> cells;

    public AbstractATM(Map<Integer, Cell> cells) {
        this.cells = new HashMap<>(cells);
    }

    @Override
    public int getBalance() {
        return cells.values().stream()
                .mapToInt(Cell::getBalance)
                .sum();
    }
}
