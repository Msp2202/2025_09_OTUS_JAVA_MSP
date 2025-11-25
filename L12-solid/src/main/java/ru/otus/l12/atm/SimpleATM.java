package ru.otus.l12.atm;

import java.util.Map;

/** Класс по управлению ячейками(Агрегация всех ячеек) */
public class SimpleATM implements ATM {
    @Override
    public void acceptCash(Map<Integer, Integer> deposit) {
        // TODO простая логика внесения
    }

    @Override
    public Map<Integer, Integer> whihdrawCash(int amount) {
        // TODO простая логика ввыдачи(используес жадный алгоритм)
        return Map.of();
    }

    @Override
    public int balance() {
        // TODO простая сумма баланса
        return 0;
    }
}
