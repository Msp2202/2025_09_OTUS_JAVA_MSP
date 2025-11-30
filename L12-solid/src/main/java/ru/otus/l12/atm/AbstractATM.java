package ru.otus.l12.atm;

import java.util.Map;
import lombok.AllArgsConstructor;
import ru.otus.l12.atm.interfaces.Balance;

@AllArgsConstructor
public abstract class AbstractATM implements Balance {
    protected final Map<Integer, Cell> cells;

    @Override
    public int getBalance() {
        return cells.values().stream().mapToInt(Cell::getBalance).sum();
    }
}
