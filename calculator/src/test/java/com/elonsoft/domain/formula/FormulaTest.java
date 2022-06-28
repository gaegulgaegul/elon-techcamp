package com.elonsoft.domain.formula;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormulaTest {

    @Test
    @DisplayName("[성공] 수식 계산")
    void given_expression_when_calculate_then_ok() {
        Numbers numbers = new Numbers();
        String first = numbers.set("4");
        String second = numbers.set("5");
        String postfix = first + second + "*";

        Formula formula = new Formula(postfix, numbers);
        double calculate = formula.calculate();

        assertEquals(calculate, 20.0);
    }

    @Test
    @DisplayName("[예외] 잘못된 수식 계산")
    void given_wrong_number_when_calculate_then_exception() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Numbers numbers = new Numbers();
                    String first = numbers.set("ㅁ");
                    String second = numbers.set("5");
                    String postfix = first + second + "*";
                    Formula formula = new Formula(postfix, numbers);
                    formula.calculate();
                },
                "[ ERROR ] 계산식을 확인해주세요."
        );
    }
}