package com.elonsoft.domain.postfix;

import com.elonsoft.domain.formula.Numbers;

/**
 * 후위 표현식 계산 인터페이스
 */
public interface PostfixProcess {

    /**
     * 수식 계산 수행
     * @param context
     * @return
     */
    double interpret(Numbers context);

    static PostfixProcess plus(PostfixProcess left, PostfixProcess right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static PostfixProcess minus(PostfixProcess left, PostfixProcess right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static PostfixProcess multiply(PostfixProcess left, PostfixProcess right) {
        return context -> left.interpret(context) * right.interpret(context);
    }

    static PostfixProcess devide(PostfixProcess left, PostfixProcess right) {
        return context -> left.interpret(context) / right.interpret(context);
    }
}
