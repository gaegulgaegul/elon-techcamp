package com.elonsoft.view;

import java.util.Scanner;

/**
 * 콘솔 입력
 */
public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * 계산식 입력
     * @return
     */
    public static String expression() {
        System.out.println("계산식을 입력하세요. ");
        return scanner.next();
    }
}
