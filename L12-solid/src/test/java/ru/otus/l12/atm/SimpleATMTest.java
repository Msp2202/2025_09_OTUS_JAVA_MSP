package ru.otus.l12.atm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.l12.atm.exceptions.WithdrawalException;
import ru.otus.l12.atm.services.ATMValidator;
import ru.otus.l12.atm.services.WithdrawalCalculator;

class SimpleATMTest {
    private Map<Integer, Cell> cells;
    private SimpleATM atm;
    private WithdrawalCalculator calculator;
    private ATMValidator validator;
    private OperationExecutor executor;

    @BeforeEach
    void setUp() {
        cells = Map.of(
                5000, new Cell(5000, 2, 100),
                1000, new Cell(1000, 5, 100),
                500, new Cell(500, 10, 100),
                100, new Cell(100, 20, 100));
        validator = new ATMValidator();
        calculator = new WithdrawalCalculator(validator);
        executor = new OperationExecutor(cells);
        atm = new SimpleATM(cells, calculator, validator, executor);
    }

    @Test
    @DisplayName(" *** Получение корректного баланса банкомата")
    void shouldReturnCorrectBalance() {
        System.out.println("=== Тест: получение баланса ===");
        int balance = atm.getBalance();
        System.out.println("Баланс банкомата: " + balance + " руб");
        assertEquals(22000, balance);
        System.out.println("✅ Баланс корректен");
    }

    @Test
    @DisplayName(" *** Внесение наличных средств")
    void shouldAcceptCash() {
        System.out.println("=== Тест: внесение наличных ===");
        System.out.println("Баланс до внесения: " + atm.getBalance() + " руб");

        atm.deposit(Map.of(1000, 3));
        int balanceAfter = atm.getBalance();

        System.out.println("Внесено: 3 банкноты по 1000 руб");
        System.out.println("Баланс после внесения: " + balanceAfter + " руб");
        assertEquals(25000, balanceAfter);
        System.out.println("✅ Средства зачислены корректно");
    }

    @Test
    @DisplayName(" *** Выдача минимальным количеством банкнот")
    void shouldWithdrawWithMinimalNotes() {
        System.out.println("=== Тест: выдача минимальным количеством банкнот ===");
        System.out.println("Запрошено: 7500 руб");
        System.out.println("Баланс до выдачи: " + atm.getBalance() + " руб");

        Map<Integer, Integer> result = atm.withdraw(7500);
        int balanceAfter = atm.getBalance();

        System.out.println("Выдано: " + result);
        System.out.println("Баланс после выдачи: " + balanceAfter + " руб");

        assertEquals(Map.of(5000, 1, 1000, 2, 500, 1), result);
        assertEquals(14500, balanceAfter);
        System.out.println("✅ Выдача произведена минимальным количеством банкнот");
    }

    @Test
    @DisplayName(" *** Ошибка при невозможности выдачи суммы")
    void shouldThrowWhenCannotWithdraw() {
        System.out.println("=== Тест: ошибка при невозможности выдачи ===");
        Map<Integer, Cell> limitedCells = Map.of(5000, new Cell(5000, 2, 100));
        SimpleATM limitedATM = new SimpleATM(limitedCells, calculator, validator, executor);

        System.out.println("Банкомат содержит только 5000-рублевые купюры");
        System.out.println("Попытка выдать 3000 руб...");

        assertThrows(WithdrawalException.class, () -> {
            limitedATM.withdraw(3000);
        });

        System.out.println("✅ Исключение выброшено корректно - выдача невозможна");
    }

    @Test
    @DisplayName(" *** Недостаточно средств на счете")
    void shouldThrowWhenInsufficientFunds() {
        System.out.println("=== Тест: недостаточно средств ===");
        System.out.println("Текущий баланс: " + atm.getBalance() + " руб");
        System.out.println("Попытка выдать 50000 руб...");

        assertThrows(IllegalStateException.class, () -> {
            atm.withdraw(50000);
        });

        System.out.println("✅ Исключение выброшено - недостаточно средств");
    }

    @Test
    @DisplayName(" *** Отрицательная сумма для выдачи")
    void shouldThrowWhenNegativeAmount() {
        System.out.println("=== Тест: отрицательная сумма ===");
        System.out.println("Попытка выдать -1000 руб...");

        assertThrows(IllegalArgumentException.class, () -> {
            atm.withdraw(-1000);
        });

        System.out.println("✅ Исключение выброшено - отрицательная сумма недопустима");
    }

    @Test
    @DisplayName(" *** Тестирование различных сумм выдачи")
    void debugVariousAmounts() {
        System.out.println("=== Отладочный тест: различные суммы ===");
        System.out.println("Начальный баланс: " + atm.getBalance() + " руб");

        testWithdrawal(3000);
        testWithdrawal(2500);
        testWithdrawal(1300);
        testWithdrawal(325);

        System.out.println("Финальный баланс: " + atm.getBalance() + " руб");
    }

    private void testWithdrawal(int amount) {
        try {
            Map<Integer, Integer> result = atm.withdraw(amount);
            System.out.println(amount + " руб → УСПЕХ: " + result);
        } catch (Exception e) {
            System.out.println(amount + " руб → ОШИБКА: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
