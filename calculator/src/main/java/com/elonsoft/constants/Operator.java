package com.elonsoft.constants;

import com.elonsoft.domain.postfix.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public enum Operator {
    PLUS('+') {
        @Override
        public boolean prioritize(char symbol) {
            return symbol == PLUS.symbol || symbol == MINUS.symbol || symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol;
        }

        @Override
        public PostfixProcess process(Stack<PostfixProcess> stack) {
            return PostfixProcess.plus(stack.pop(), stack.pop());
        }

    },
    MINUS('-') {
        @Override
        public boolean prioritize(char symbol) {
            return symbol == MINUS.symbol || symbol == PLUS.symbol || symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol;
        }

        @Override
        public PostfixProcess process(Stack<PostfixProcess> stack) {
            PostfixProcess right = stack.pop();
            PostfixProcess left = stack.pop();
            return PostfixProcess.minus(left, right);
        }
    },
    MULTIPLY('*') {
        @Override
        public boolean prioritize(char symbol) {
            return symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol;
        }

        @Override
        public PostfixProcess process(Stack<PostfixProcess> stack) {
            return PostfixProcess.multiply(stack.pop(), stack.pop());
        }
    },
    DIVIDE('/') {
        @Override
        public boolean prioritize(char symbol) {
            return symbol == DIVIDE.symbol || symbol == MULTIPLY.symbol;
        }

        @Override
        public PostfixProcess process(Stack<PostfixProcess> stack) {
            PostfixProcess right = stack.pop();
            PostfixProcess left = stack.pop();
            return PostfixProcess.devide(left, right);
        }
    };

    private char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    public static Operator symbolFor(char symbol) {
        if (symbol == PLUS.symbol) return PLUS;
        if (symbol == MINUS.symbol) return MINUS;
        if (symbol == MULTIPLY.symbol) return MULTIPLY;
        if (symbol == DIVIDE.symbol) return DIVIDE;
        return null;
    }

    public static List<Character> symbols() {
        return Arrays.asList(PLUS.symbol, MINUS.symbol, MULTIPLY.symbol, DIVIDE.symbol);
    }

    public abstract boolean prioritize(char symbol);

    public abstract PostfixProcess process(Stack<PostfixProcess> stack);
}
