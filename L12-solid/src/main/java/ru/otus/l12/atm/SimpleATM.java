package ru.otus.l12.atm;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ru.otus.l12.atm.interfaces.FullFunctionalATM;
import ru.otus.l12.atm.services.ATMValidator;
import ru.otus.l12.atm.services.WithdrawalCalculator;

/**
 * Класс по управлению ячейками(Агрегация всех ячеек и полного функционала БАНКОМАТА)
 */
@Slf4j
public class SimpleATM extends AbstractATM implements FullFunctionalATM {
    private final WithdrawalCalculator calculator;
    private final ATMValidator validator;
    private final OperationExecutor operation;

    public SimpleATM(
            Map<Integer, Cell> cells,
            WithdrawalCalculator calculator,
            ATMValidator validator,
            OperationExecutor operation) {
        super(cells);
        this.calculator = calculator;
        this.validator = validator;
        this.operation = operation;
    }

    /**
     * Метод пополнения
     */
    @Override
    public void deposit(Map<Integer, Integer> money) {
        validator.validateDeposit(money, cells);
        operation.executeDeposit(money);
        log.info("Баланс пополнен. общая сумма баланса: {}, руб", getBalance());
    }

    /**
     * Метод выдачи.
     */
    @Override
    public Map<Integer, Integer> withdraw(int amount) {
        if (amount > getBalance()) {
            throw new IllegalStateException(
                    "Недостаточно средств на счете. " + "Запрошено: " + amount + ", доступно: " + getBalance());
        }
        Map<Integer, Integer> withdrawalPlan = calculator.calculateWithdrawal(amount, cells);
        operation.executeWithdrawal(withdrawalPlan);
        log.info("Списание средст выполнено успешно. Общая остаток на балансе: {}, руб", getBalance());
        return withdrawalPlan;
    }
}
