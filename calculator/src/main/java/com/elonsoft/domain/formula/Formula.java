package com.elonsoft.domain.formula;

import com.elonsoft.constants.Operator;
import com.elonsoft.domain.postfix.PostfixProcessor;
import com.elonsoft.domain.postfix.VariableProcessor;

import java.util.Stack;

/**
 * 계산 수행
 *  - 후위 표현식 계산
 */
public class Formula {
    private final String postfixExpression;         // 후위 표현 계산식
    private final Numbers numbers;                  // 치환된 숫자 정보 context

    private Stack<PostfixProcessor> stack;

    public Formula(String postfixExpression, Numbers numbers) {
        this.postfixExpression = postfixExpression;
        this.numbers = numbers;
        this.stack = new Stack<>();
    }

    /**
     * 계산을 수행한다.
     * @return
     */
    public double calculate() {
        try {
            return formatting(restore().interpret(this.numbers));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ ERROR ] 계산식을 확인해주세요.");
        }
    }

    /**
     * 소수점 3자리 버림
     * @param calculation
     * @return
     */
    private double formatting(double calculation) {
        return Math.floor(calculation * 1000) / 1000.0;
    }

    /**
     * 후위 표현식을 계산 처리 클래스, 값 처리 클래스로 변환해준다.
     * @return
     */
    private PostfixProcessor restore() {
        for (char character : this.postfixExpression.toCharArray()) {
            setProcessor(character);
        }
        return this.stack.pop();
    }

    /**
     * 후위 표현계산 객체 추가
     * @param character
     */
    private void setProcessor(char character) {
        Operator operator = Operator.symbolOf(character);

        if (operator != null) {
            this.stack.push(operator.process(this.stack));
            return;
        }
        this.stack.push(new VariableProcessor(character));
    }
}
