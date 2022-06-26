package com.elonsoft.controller;

import com.elonsoft.domain.formula.Formula;
import com.elonsoft.domain.formula.FormulaReplacer;
import com.elonsoft.domain.postfix.PostfixParser;
import com.elonsoft.view.InputView;
import com.elonsoft.view.ResultView;

/**
 * 계산기
 */
public class Calculator {
    private final FormulaReplacer formulaReplacer;
    private final PostfixParser postfixParser;

    public Calculator() {
        this.formulaReplacer = new FormulaReplacer();
        this.postfixParser = new PostfixParser();
    }

    /**
     * 계산 수행
     */
    public void calculate() {
        String expression = InputView.expression();
        formulaReplacer.replace(expression);
        String postfix = postfixParser.parse(formulaReplacer.getExpression());
        Formula formula = new Formula(postfix, formulaReplacer.getNumbers());
        ResultView.print(expression, formula.calculate());
    }
}
