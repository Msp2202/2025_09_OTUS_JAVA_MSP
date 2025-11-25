package ru.otus.l12.atm;

import ru.otus.l12.atm.interfaces.FullFunctionalATM;

import java.util.Map;

/** Класс по управлению ячейками(Агрегация всех ячеек) */
public class SimpleATM extends AbstractATM implements FullFunctionalATM {

    public SimpleATM(Map<Integer, Cell> cells) {
        super(cells);
    }
//    /** Используем более ясный термин money для понимания, что можем вносить как банкноты, так и в перспективе расчет по безналу*/
//    @Override
//    public void acceptCash(Map<Integer, Integer> money) {
//        // TODO простая логика внесения
//    }
//
//    @Override
//    public Map<Integer, Integer> withdrawCash(int amount) {
//        // TODO простая логика выдачи(использует жадный алгоритм)
//        return Map.of();
//    }
//
//    @Override
//    public int balance() {
//        // TODO простая сумма баланса
//        return 0;
//    }

    // TODO: getBalance() уже наследуется от AbstractATM
    @Override
    public void deposit(Map<Integer, Integer> money) {
        // TODO: логика внесения
    }

    @Override
    public Map<Integer, Integer> withdraw(int amount) {
        // TODO: логика выдачи
        return Map.of();
    }
}
