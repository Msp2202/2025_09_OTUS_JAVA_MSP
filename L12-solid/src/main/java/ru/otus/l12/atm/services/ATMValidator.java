package ru.otus.l12.atm.services;

import java.util.Map;
import ru.otus.l12.atm.Cell;
import ru.otus.l12.atm.exceptions.CellOverflowException;

public class ATMValidator {

    /** Проверяем возможность внесения денежных средств во все ячейки */
    public void validateDeposit(Map<Integer, Integer> money, Map<Integer, Cell> cells) {
        if (money == null || money.isEmpty()) {
            throw new IllegalArgumentException("Пустой депозит");
        }
        for (Map.Entry<Integer, Integer> entry : money.entrySet()) {
            int nominal = entry.getKey();
            int quantity = entry.getValue();

            validateNominal(nominal, cells);
            validateQuantity(quantity);
            validateCellCapasity(nominal, quantity, cells);
        }
    }

    private void validateCellCapasity(int nominal, int quantity, Map<Integer, Cell> cells) {
        Cell cell = cells.get(nominal);
        if (!cell.canDeposit(quantity)) {
            throw new CellOverflowException(nominal, cell.getCount(), cell.getCapacity(), quantity);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество банкнот должно быть положительным: " + quantity);
        }
    }

    private void validateNominal(int nominal, Map<Integer, Cell> cells) {
        if (!cells.containsKey(nominal)) {
            throw new UnsupportedOperationException(String.valueOf(nominal));
        }
    }

    public void validateWithdrawal(int amount, Map<Integer, Cell> cells) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }

        if (cells == null || cells.isEmpty()) {
            throw new IllegalArgumentException("Нет ячеек для выдачи");
        }
    }
}
