package ru.otus.l12.atm.interfaces;

import java.util.Map;

public interface ATM {
    /** Внесение: номинал, количество*/
    void acceptCash(Map<Integer, Integer> deposit);
    /** Суммы выдачи*/
    Map<Integer,Integer> whihdrawCash(int amount);
    /** Информация о балансе*/
    int balance();

}
