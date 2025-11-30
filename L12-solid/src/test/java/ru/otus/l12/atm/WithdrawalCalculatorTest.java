package ru.otus.l12.atm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.l12.atm.exceptions.WithdrawalException;
import ru.otus.l12.atm.services.ATMValidator;
import ru.otus.l12.atm.services.WithdrawalCalculator;

class WithdrawalCalculatorTest {

    private WithdrawalCalculator calculator;
    private ATMValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ATMValidator();
        calculator = new WithdrawalCalculator(validator);
    }

    @Test
    void shouldCalculateWithdrawal() {
        Map<Integer, Cell> availableCells = Map.of(
                5000, new Cell(5000, 2, 100),
                1000, new Cell(1000, 5, 100),
                500, new Cell(500, 10, 100),
                100, new Cell(100, 20, 100));

        Map<Integer, Integer> result = calculator.calculateWithdrawal(7500, availableCells);

        assertEquals(Map.of(5000, 1, 1000, 2, 500, 1), result);
    }

    @Test
    void shouldThrowWhenAmountCannotBeIssued() {
        Map<Integer, Cell> availableCells = Map.of(5000, new Cell(5000, 2, 100));

        assertThrows(WithdrawalException.class, () -> {
            calculator.calculateWithdrawal(3000, availableCells);
        });
    }
}
