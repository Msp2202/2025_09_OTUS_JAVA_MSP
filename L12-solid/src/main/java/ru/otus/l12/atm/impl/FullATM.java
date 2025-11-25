package ru.otus.l12.atm.impl;

import ru.otus.l12.atm.AbstractATM;
import ru.otus.l12.atm.Cell;
import ru.otus.l12.atm.interfaces.FullFunctionalATM;

import java.util.Map;

/** Класс для работы с разным функционалом Банкомата -> FullATM полный */
// TODO: Реализовать классы
// DepositOnlyATM -> только депозит + баланс
// WithdrawOnlyATM  -> только только вывод + баланс
public class FullATM extends AbstractATM implements FullFunctionalATM {

    public FullATM(Map<Integer, Cell> cells) {
        super(cells);
    }

    @Override
    public void deposit(Map<Integer, Integer> money) {

    }

    @Override
    public Map<Integer, Integer> withdraw(int amount) {
        return Map.of();
    }
}
