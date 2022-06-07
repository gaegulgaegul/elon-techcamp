package com.elonsoft.view;

/**
 * 결과 출력
 */
public class ResultView {

    /**
     * 계산 결과 출력
     * @param expression
     * @param calculation
     */
    public static void print(String expression, double calculation) {
        System.out.println(expression + " = " + calculation);
    }
}
