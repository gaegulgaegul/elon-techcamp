package com.elonsoft.domain.formula;

import com.elonsoft.constants.Operator;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 계산식을 변형하는 클래스
 *  - 숫자는 단일 문자열로 치환
 *  - 중위 표현식 -> 후위 표현식
 */
public class FormulaParser {
    private final List<Character> brackets = Arrays.asList('(', ')');
    private final String numberReplaceRegex = "[^0-9.]";                                                // 숫자 치환을 위해 검사 정규식
    private final String syntaxRegex = "[0-9|\\.|\\+|\\-|\\*|\\/|\\(|\\)]*$";                           // 계산식 유효성 검사 정규식
    private final String numberOfEndToEndRegex = "^\\d{1}[0-9|\\.|\\+|\\-|\\*|\\/|\\(|\\)]*\\d{1}$";    // 계산식의 앞뒤 문자 타입이 숫자 정규식

    private final String infixExpression;           // 입력 받은 중위 표현 계산식
    private String replacedExpression;              // 숫자가 치환된 계산식
    private String postfixExpression;               // 후위 표현 계산식
    private Numbers numbers;                        // 치환된 숫자를 가지는 context

    public FormulaParser(String infixExpression) {
        this.infixExpression = infixExpression;
        this.replacedExpression = infixExpression;
        this.numbers = new Numbers();
    }

    /**
     * 계산식 수행 클래스로 반환
     * @return
     */
    public Formula toFormula() {
        validate();
        replaceNumbers();
        parsePostfix();
        return new Formula(this.postfixExpression, this.numbers);
    }

    /**
     * 유효성 검사
     */
    private void validate() {
        if (!this.infixExpression.matches(syntaxRegex)) throw new IllegalArgumentException("[ ERROR ] 계산식을 다시 입력해주세요.");
        if (!this.infixExpression.matches(numberOfEndToEndRegex))
            throw new IllegalArgumentException("[ ERROR ] 계산식의 시작 또는 끝은 숫자를 입력해주세요.");
    }

    /**
     * 계산식에서 숫자를 단일 문자로 치환
     */
    private void replaceNumbers() {
        for (String stringNumber : this.infixExpression.split(this.numberReplaceRegex)) {
            if (stringNumber.length() == 0) {
                continue;
            }

            String key = this.numbers.set(stringNumber);
            this.replacedExpression = this.replacedExpression.replaceFirst(stringNumber, key);
        }
    }

    /**
     * 후위 표현식으로 변환
     */
    private void parsePostfix() {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char character : this.replacedExpression.toCharArray()) {
            if (isOperators(character)) {
                setOperators(stringBuilder, stack, character);
                continue;
            }
            stringBuilder.append(character);
        }

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        this.postfixExpression = stringBuilder.toString();
    }

    /**
     * 후위 표현식 변환 중 계산식 우선순위에 따라 수식을 stringBuilder에 입력한다.
     * @param stringBuilder
     * @param stack
     * @param character
     */
    private void setOperators(StringBuilder stringBuilder, Stack<Character> stack, char character) {
        if (')' == character) {
            setOperatorsWithBrackets(stringBuilder, stack);
            return;
        }
        prioryFor(stringBuilder, stack, character);
    }

    /**
     * 수식 우선순위에 따라 stringBuilder에 입력한다.
     * @param stringBuilder
     * @param stack
     * @param character
     */
    private void prioryFor(StringBuilder stringBuilder, Stack<Character> stack, char character) {
        while (!stack.isEmpty() && Operator.symbolFor(character).prioritize(stack.peek())) {
            stringBuilder.append(stack.pop());
        }
        stack.push(character);
    }

    /**
     * 사용자가 우선순위를 지정하지 않은 계산식 부분을 stringBuilder에 입력한다.
     * @param stringBuilder
     * @param stack
     */
    private void setOperatorsWithBrackets(StringBuilder stringBuilder, Stack<Character> stack) {
        while (!stack.isEmpty() && stack.peek() != '(') {
            stringBuilder.append(stack.pop());
        }
        // 스택의 시작 괄호('(')를 제거한다.
        stack.pop();
    }

    /**
     * 단일 문자가 수식 또는 소괄호인지 확인한다.
     * @param character
     * @return
     */
    private boolean isOperators(char character) {
        return Operator.symbols().contains(character) || this.brackets.contains(character);
    }
}
