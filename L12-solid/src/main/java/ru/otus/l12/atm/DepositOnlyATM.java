package ru.otus.l12.atm;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import ru.otus.l12.atm.interfaces.Depositable;
import ru.otus.l12.atm.models.Cell;
import ru.otus.l12.atm.services.ATMValidator;
import ru.otus.l12.atm.services.OperationExecutor;

/**
 * Класс по управлению ячейками(Агрегация всех ячеек и БАНКОМАТА только с пополнением)
 */
@Slf4j
public class DepositOnlyATM extends AbstractATM implements Depositable {
    private final ATMValidator validator;
    private final OperationExecutor operation;

    public DepositOnlyATM(Map<Integer, Cell> cells,
                          ATMValidator validator, OperationExecutor operation) {
        super(cells);
        this.validator = validator;
        this.operation = operation;
    }
    /** Метод пополнения */
    @Override
    public void deposit(Map<Integer, Integer> money) {
        validator.validateDeposit(money, cells);
        operation.executeDeposit(money);
        log.info("Баланс пополнен. общая сумма баланса: {}, руб", getBalance());
    }
}
