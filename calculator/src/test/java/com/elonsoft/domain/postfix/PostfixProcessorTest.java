package com.elonsoft.domain.postfix;

import com.elonsoft.domain.formula.Numbers;
import com.elonsoft.domain.postfix.PostfixProcessor;
import com.elonsoft.domain.postfix.VariableProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostfixProcessorTest {

    private final PostfixProcessor left;
    private final PostfixProcessor right;
    private final Numbers numbers;

    public PostfixProcessorTest() {
        this.left = new VariableProcessor('0');
        this.right = new VariableProcessor('1');
        this.numbers = new Numbers();
        this.numbers.set("5");
        this.numbers.set("3");
    }

    @Test
    @DisplayName("[성공] 더하기")
    void plus() {
        PostfixProcessor plus = PostfixProcessor.plus(left, right);
        assertEquals(plus.interpret(numbers), 8);
    }

    @Test
    @DisplayName("[성공] 빼기")
    void minus() {
        PostfixProcessor minus = PostfixProcessor.minus(left, right);
        assertEquals(minus.interpret(numbers), 2);
    }

    @Test
    @DisplayName("[성공] 곱하기")
    void multiply() {
        PostfixProcessor multiply = PostfixProcessor.multiply(left, right);
        assertEquals(multiply.interpret(numbers), 15);
    }

    @Test
    @DisplayName("[성공] 나누기")
    void divide() {
        PostfixProcessor divide = PostfixProcessor.divide(left, right);
        assertEquals(divide.interpret(numbers), 1.6666666666666667);
    }
}