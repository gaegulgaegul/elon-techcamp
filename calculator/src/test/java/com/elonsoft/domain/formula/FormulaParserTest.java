package com.elonsoft.domain.formula;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormulaParserTest {

    @Test
    @DisplayName("[성공] 수식계산 parser 생성 및 수식계산 객체 생성")
    void given_expression_when_create_parser_then_ok() {
        String expression = "3*4/5";
        FormulaParser formulaParser = new FormulaParser(expression);
        Formula formula = formulaParser.toFormula();
        assertEquals(formula.getClass(), Formula.class);
    }

    @Test
    @DisplayName("[예외] 수식계산 객체 생성 예외 발생")
    void given_wrong_expression_when_create_parser_then_exception() {
        assertAll(
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> {
                                String expression = "3*4/";
                                FormulaParser formulaParser = new FormulaParser(expression);
                                formulaParser.toFormula();
                            },
                            "[ ERROR ] 계산식의 시작 또는 끝은 숫자를 입력해주세요.");
                },
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> {
                                String expression = "ㅁ/ㅇer2";
                                FormulaParser formulaParser = new FormulaParser(expression);
                                formulaParser.toFormula();
                            },
                            "[ ERROR ] 계산식을 다시 입력해주세요.");
                }
        );
    }
}