package com.elonsoft.domain.postfix;

import com.elonsoft.domain.formula.Numbers;

/**
 * 후위 표현식 계산 인터페이스
 */
public interface PostfixProcessor {

    /**
     * 수식 계산 수행
     * @param context
     * @return
     */
    double interpret(Numbers context);

    /**
     * 더하기
     * @param left
     * @param right
     * @return
     */
    static PostfixProcessor plus(PostfixProcessor left, PostfixProcessor right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    /**
     * 빼기
     * @param left
     * @param right
     * @return
     */
    static PostfixProcessor minus(PostfixProcessor left, PostfixProcessor right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    /**
     * 곱하기
     * @param left
     * @param right
     * @return
     */
    static PostfixProcessor multiply(PostfixProcessor left, PostfixProcessor right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    /**
     * 나누기
     * @param left
     * @param right
     * @return
     */
    static PostfixProcessor divide(PostfixProcessor left, PostfixProcessor right) {
        return context -> left.interpret(context) / right.interpret(context);
    }
}
