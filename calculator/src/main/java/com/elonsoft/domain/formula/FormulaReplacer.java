package com.elonsoft.domain.formula;

/**
 * 계산식을 변형하는 클래스
 *  - 숫자는 단일 문자열로 치환
 */
public class FormulaReplacer {
    private static final String NUMBER_REPLACE_REGEX = "[^0-9.]";                       // 숫자 치환을 위해 검사 정규식
    private static final String SYNTAX_REGEX = "[0-9|\\.|\\+|\\-|\\*|\\/|\\(|\\)]*$";   // 계산식 유효성 검사 정규식

    private String expression;      // 입력 받은 중위 표현 계산식
    private Numbers numbers;        // 치환된 숫자를 가지는 context

    /**
     * 계산식 수행 클래스로 반환
     * @return
     */
    public void replace(String syntax) {
        validate(syntax);
        this.numbers = new Numbers();
        this.expression = replaceNumbers(syntax);
    }

    /**
     * 유효성 검사
     * @param syntax
     */
    private void validate(String syntax) {
        if (!syntax.matches(SYNTAX_REGEX)) {
            throw new IllegalArgumentException("[ ERROR ] 계산식을 다시 입력해주세요.");
        }
    }

    /**
     * 계산식에서 숫자를 단일 문자로 치환
     * @param syntax
     */
    private String replaceNumbers(String syntax) {
        String expression = syntax;
        for (String stringNumber : syntax.split(NUMBER_REPLACE_REGEX)) {
            expression = replaceNumber(stringNumber, expression);
        }
        return expression;
    }

    /**
     * 계산식 치환
     * @param stringNumber
     * @param expression
     */
    private String replaceNumber(String stringNumber, String expression) {
        if (stringNumber.length() == 0) {
            return expression;
        }

        String key = this.numbers.set(stringNumber);
        return expression.replaceFirst(stringNumber, key);
    }

    public String getExpression() {
        return expression;
    }

    public Numbers getNumbers() {
        return numbers;
    }

}
