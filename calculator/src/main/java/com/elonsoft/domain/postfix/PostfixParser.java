package com.elonsoft.domain.postfix;

import com.elonsoft.constants.Operator;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 후위 표현식 변환 parser
 */
public class PostfixParser {
    private static final char START_PARENTHESES = '(';
    private static final char END_PARENTHESES = ')';
    private static final List<Character> PARENTHESES = Arrays.asList(START_PARENTHESES, END_PARENTHESES);

    private StringBuilder stringBuilder;
    private Stack<Character> stack;

    public PostfixParser() {
        this.stringBuilder = new StringBuilder();
        this.stack = new Stack<>();
    }

    /**
     * 후위 표현식으로 변환
     */
    public String parse(String infix) {
        for (char character : infix.toCharArray()) {
            setCharacter(character);
        }

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.toString();
    }

    /**
     * 단일 문자 설정
     * @param character
     */
    private void setCharacter(char character) {
        if (isOperators(character)) {
            setOperators(character);
            return;
        }
        stringBuilder.append(character);
    }

    /**
     * 후위 표현식 변환 중 계산식 우선순위에 따라 수식을 입력한다.
     * @param character
     */
    private void setOperators(char character) {
        if (END_PARENTHESES == character) {
            setOperatorsWithParentheses();
            return;
        }
        prioryFor(character);
    }

    /**
     * 수식 우선순위에 따라 문자를 배치한다.
     * @param character
     */
    private void prioryFor(char character) {
        while (isHighPriory(character)) {
            stringBuilder.append(stack.pop());
        }
        stack.push(character);
    }

    /**
     * 입력된 사칙연산 기호의 우선순위가 기존 사칙연산 기호보다 높은지 확인
     * @param character
     * @return
     */
    private boolean isHighPriory(char character) {
        if (stack.isEmpty()) return false;

        Operator arithmetic = Operator.symbolOf(character);
        if (arithmetic == null) {
            return false;
        }
        return arithmetic.prioryThen(stack.peek());
    }

    /**
     * 사용자가 입력한 계산식 우선 순위에 따라 입력한다.
     */
    private void setOperatorsWithParentheses() {
        while (isNotStartParentheses()) {
            stringBuilder.append(stack.pop());
        }
        // 스택의 시작 괄호('(')를 제거한다.
        stack.pop();
    }

    /**
     * 스택의 값이 시작 소괄호가 아닌 경우 true
     * @return
     */
    private boolean isNotStartParentheses() {
        return !stack.isEmpty() && stack.peek() != START_PARENTHESES;
    }

    /**
     * 단일 문자가 수식 또는 소괄호인지 확인한다.
     * @param character
     * @return
     */
    private boolean isOperators(char character) {
        return Operator.symbols().contains(character) || PARENTHESES.contains(character);
    }
}
