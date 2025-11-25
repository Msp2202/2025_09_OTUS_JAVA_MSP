package ru.otus.l12.atm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
        return count * nominal;
    }
    /** ===== Валидация, где quantity количество купюр которые планируем добавить или выдать. ======*/
    public boolean canDeposit(int quantity) {
        return count + quantity <= capacity;
    }

    public boolean canWithdraw(int quantity) {
        return quantity <= count;
    }
    /** ===== Метод добавления ======*/
    void depositNotes(int quantity) {
        if (!canDeposit(quantity)) throw new IllegalStateException("Ячейка переполнена");
        count += quantity;
    }
    /** ===== Метод выдачи ======*/
    void withdrawNotes(int quantity) {
        if (!canWithdraw(quantity)) throw new IllegalStateException("Недостаточно банкнот");
        count -= quantity;
    }
}
