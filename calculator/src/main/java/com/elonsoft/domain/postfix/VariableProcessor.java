package com.elonsoft.domain.postfix;

import com.elonsoft.domain.formula.Numbers;

/**
 * 값을 처리하는 후위 표현식 계산 인터페이스 구현체
 */
public class VariableProcessor implements PostfixProcessor {

    private String key;         // 치환된 숫자의 key

    public VariableProcessor(char key) {
        this.key = String.valueOf(key);
    }

    @Override
    public double interpret(Numbers context) {
        return context.get(key).doubleValue();
    }
}
