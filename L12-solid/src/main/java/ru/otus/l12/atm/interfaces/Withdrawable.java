package ru.otus.l12.atm.interfaces;

import java.util.Map;

public interface Withdrawable {
    Map<Integer, Integer> withdraw(int amount);
}
