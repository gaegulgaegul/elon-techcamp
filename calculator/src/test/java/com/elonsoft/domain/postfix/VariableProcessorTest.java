package com.elonsoft.domain.postfix;

import com.elonsoft.domain.formula.Numbers;
import com.elonsoft.domain.postfix.VariableProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableProcessorTest {

    private final Numbers numbers;

    public VariableProcessorTest() {
        this.numbers = new Numbers();
        this.numbers.set("1");
    }

    @Test
    @DisplayName("[성공] 후위 연산 계산식 키에 해당하는 값 반환")
    void get_value() {
        VariableProcessor variableProcessor = new VariableProcessor('0');
        assertEquals(variableProcessor.interpret(numbers), 1.0);
    }
}