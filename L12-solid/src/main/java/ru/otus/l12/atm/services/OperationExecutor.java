package ru.otus.l12.atm.services;

import ru.otus.l12.atm.models.Cell;

import java.util.HashMap;
import java.util.Map;

public class OperationExecutor {
    private final Map<Integer, Cell> cells;

    public OperationExecutor(Map<Integer, Cell> cells) {
        this.cells = new HashMap<>(cells);
    }

    public void executeDeposit(Map<Integer, Integer> money) {
        for (Map.Entry<Integer, Integer> entry : money.entrySet()) {
            Cell cell = cells.get(entry.getKey()); // nominal
            cell.depositNotes(entry.getValue()); // quantity
        }
    }

    public void executeWithdrawal(Map<Integer, Integer> plan) {
        for (Map.Entry<Integer, Integer> entry : plan.entrySet()) {
            Cell cell = cells.get(entry.getKey());
            cell.withdrawNotes(entry.getValue());
        }
    }
}
