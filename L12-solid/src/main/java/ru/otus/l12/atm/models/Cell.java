package ru.otus.l12.atm.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.otus.l12.atm.exceptions.CellOverflowException;
import ru.otus.l12.atm.exceptions.InsufficientNotesException;

/**
 * Класс ячейка отвечает только за хранение одного номинала
 */
@RequiredArgsConstructor
@Getter
public class Cell {
    private final int nominal;
    private int count;
    private final int capacity;

    public int getBalance() {
        return nominal * count;
    }
    /** ===== Валидация, где quantity количество купюр которые планируем добавить. ======*/
    public boolean canDeposit(int quantity) {
        return count + quantity <= capacity;
    }

    /** ===== Валидация, где quantity количество купюр которые планируем выдать. ======*/
    public boolean canWithdraw(int quantity) {
        return quantity <= count;
    }
    /** ===== Метод добавления ======*/
    void depositNotes(int quantity) {
        if (!canDeposit(quantity)) throw new CellOverflowException(nominal, count, capacity, quantity);
        count += quantity;
    }
    /** ===== Метод выдачи ======*/
    void withdrawNotes(int quantity) {
        if (!canWithdraw(quantity)) {
            throw new InsufficientNotesException(
                    "Недостаточно банкнот номиналом %d, запрошено; %d, а доступно: %d", nominal, quantity, count);
        }
        count -= quantity;
    }
}
