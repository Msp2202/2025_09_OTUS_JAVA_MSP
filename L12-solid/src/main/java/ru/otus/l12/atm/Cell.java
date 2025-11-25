package ru.otus.l12.atm;

import lombok.Data;

/** Класс ячейка отвечает только за хранение одного номинала*/
 @Data
public class Cell {
    private final int nominal;
    private final int count;

    // TODO метод по работе с одной ячейкой
}
