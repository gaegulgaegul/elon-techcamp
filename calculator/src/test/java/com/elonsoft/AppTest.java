package com.elonsoft;

import com.elonsoft.domain.formula.Formula;
import com.elonsoft.domain.formula.FormulaParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    @Test
    @DisplayName("[성공] 더하기")
    void given_value_add_when_calculation_then_ok() {
        String input = "2+1";
        FormulaParser parser = new FormulaParser(input);
        Formula formula = parser.toFormula();
        assertEquals(formula.calculate(), 3.0);
    }

    @Test
    @DisplayName("[성공] 빼기")
    void given_value_minus_when_calculation_then_ok() {
        String input = "2-1";
        FormulaParser parser = new FormulaParser(input);
        Formula formula = parser.toFormula();
        assertEquals(formula.calculate(), 1.0);
    }

    @Test
    @DisplayName("[성공] 곱하기")
    void given_value_multiply_when_calculation_then_ok() {
        String input = "8*7";
        FormulaParser parser = new FormulaParser(input);
        Formula formula = parser.toFormula();
        assertEquals(formula.calculate(), 56.0);
    }

    @Test
    @DisplayName("[성공] 나누기")
    void given_value_divide_when_calculation_then_ok() {
        String input = "60/5";
        FormulaParser parser = new FormulaParser(input);
        Formula formula = parser.toFormula();
        assertEquals(formula.calculate(), 12.0);
    }

    @Test
    @DisplayName("[성공] 나누기 소수점 3자리 버림")
    void given_value_divide_when_calculation_then_throwing_away_three_decimal_places_ok() {
        String input = "5/3";
        FormulaParser parser = new FormulaParser(input);
        Formula formula = parser.toFormula();
        assertEquals(formula.calculate(), 1.666);
    }

    @Test
    @DisplayName("[예외] 숫자가 아닌 문자를 이용한 계산")
    void given_text_when_calculation_then_exception() {
        String input = "abc/5";
        assertThrows(IllegalArgumentException.class, () -> {
            FormulaParser parser = new FormulaParser(input);
            Formula formula = parser.toFormula();
            formula.calculate();
        }, "[ ERROR ] 계산식을 다시 입력해주세요.");
    }
}