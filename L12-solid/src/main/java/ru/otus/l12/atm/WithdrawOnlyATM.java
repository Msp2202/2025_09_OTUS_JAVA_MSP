package ru.otus.l12.atm;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ru.otus.l12.atm.interfaces.Withdrawable;
import ru.otus.l12.atm.services.ATMValidator;
import ru.otus.l12.atm.services.WithdrawalCalculator;

/**
 * Класс по управлению ячейками(Агрегация всех ячеек)
 */
@Slf4j
public class WithdrawOnlyATM extends AbstractATM implements Withdrawable {
    private final ATMValidator validator;
    private final WithdrawalCalculator calculator;
    private final OperationExecutor operation;

    public WithdrawOnlyATM(
            Map<Integer, Cell> cells,
            ATMValidator validator,
            WithdrawalCalculator calculator,
            OperationExecutor operation) {
        super(cells);
        this.validator = validator;
        this.calculator = calculator;
        this.operation = operation;
    }

    /**
     * Метод выдачи.
     */
    @Override
    public Map<Integer, Integer> withdraw(int amount) {
        validator.validateWithdrawal(amount, cells);
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
