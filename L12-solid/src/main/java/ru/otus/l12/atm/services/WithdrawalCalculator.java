package ru.otus.l12.atm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.otus.l12.atm.exceptions.WithdrawalException;
import ru.otus.l12.atm.models.Cell;

public class WithdrawalCalculator {
    private final ATMValidator validator;

    public WithdrawalCalculator(ATMValidator validator) {
        this.validator = validator;
    }

    public Map<Integer, Integer> calculateWithdrawal(int amount, Map<Integer, Cell> cells) {
        validator.validateWithdrawal(amount, cells);

        /** Определяем номинал и его количество */
        Map<Integer, Integer> plan = new HashMap<>();
        int remaining = amount;

        /** Жадный алгоритм, с сортировкой от большего к меньшему */
        List<Cell> sortedCells = getSortedCell(cells);
        for (Cell cell : sortedCells) {
            int nominal = cell.getNominal();
            int maxToTake =
                    Math.min( // Math.min() гарантирует, что мы возьмем не больше, чем есть в ячейке. -> maxToTake =
                            // min(7800/5000=1, 2) = 1 => Берем 1 из 2 доступных
                            remaining / nominal, // Сколько нужно по сумме (целочисленное деление)
                            cell.getCount() // Сколько есть физически
                            );

            if (maxToTake > 0) {
                plan.put(nominal, maxToTake);
                remaining -= maxToTake * nominal;
            }
            if (remaining == 0) {
                break;
            }
        }

        if (remaining > 0) {
            throw new WithdrawalException(
                    "Невозможно выдать сумму " + amount + " доступными номиналами. Осталось:" + remaining);
        }

        return plan;
    }

    /** Метод сортировки от большего к меньшему */
    private List<Cell> getSortedCell(Map<Integer, Cell> cells) {
        return cells.values().stream()
                .sorted((c1, c2) -> Integer.compare(c2.getNominal(), c1.getNominal()))
                .collect(Collectors.toList());
    }
}
