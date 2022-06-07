package com.elonsoft.controller;

import com.elonsoft.domain.formula.Formula;
import com.elonsoft.domain.formula.FormulaParser;
import com.elonsoft.view.InputView;
import com.elonsoft.view.ResultView;

/**
 * 계산기
 */
public class Calculator {

    /**
     * 계산 수행
     */
    public void calculation() {
        String expression = InputView.expression();
        FormulaParser parser = new FormulaParser(expression);
        Formula formula = parser.toFormula();
        ResultView.print(expression, formula.calculate());
    }
}
