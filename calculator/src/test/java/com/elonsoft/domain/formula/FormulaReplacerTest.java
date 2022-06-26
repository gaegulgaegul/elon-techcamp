package com.elonsoft.domain.formula;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormulaReplacerTest {

    private FormulaReplacer formulaReplacer;

    public FormulaReplacerTest() {
        this.formulaReplacer = new FormulaReplacer();
    }

    @Test
    @DisplayName("[성공] 수식계산 parser 생성 및 수식계산 객체 생성")
    void given_expression_when_create_parser_then_ok() {
        Numbers numbers = new Numbers();
        numbers.set("3");
        numbers.set("4");
        numbers.set("5");
        formulaReplacer.replace("3*4/5");
        assertEquals(formulaReplacer.getExpression(), "0*1/2");
        assertEquals(formulaReplacer.getNumbers(), numbers);
    }

    @Test
    @DisplayName("[예외] 수식계산 객체 생성 예외 발생")
    void given_wrong_expression_when_create_parser_then_exception() {
        assertThrows(
                IllegalArgumentException.class,
                () -> formulaReplacer.replace("ㅁ/ㅇer2"),
                "[ ERROR ] 계산식을 다시 입력해주세요.");
    }
}