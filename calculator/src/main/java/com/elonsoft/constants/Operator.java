package com.elonsoft.constants;

import com.elonsoft.domain.postfix.PostfixProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public enum Operator {
    PLUS('+') {
        @Override
        public boolean prioryThen(char symbol) {
            return symbol == PLUS.symbol || symbol == MINUS.symbol || symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol;
        }

        @Override
        public PostfixProcessor process(Stack<PostfixProcessor> stack) {
            return PostfixProcessor.plus(stack.pop(), stack.pop());
        }

    },
    MINUS('-') {
        @Override
        public boolean prioryThen(char symbol) {
            return symbol == MINUS.symbol || symbol == PLUS.symbol || symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol;
        }

        @Override
        public PostfixProcessor process(Stack<PostfixProcessor> stack) {
            PostfixProcessor right = stack.pop();
            PostfixProcessor left = stack.pop();
            return PostfixProcessor.minus(left, right);
        }
    },
    MULTIPLY('*') {
        @Override
        public boolean prioryThen(char symbol) {
            return symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol;
        }

        @Override
        public PostfixProcessor process(Stack<PostfixProcessor> stack) {
            return PostfixProcessor.multiply(stack.pop(), stack.pop());
        }
    },
    DIVIDE('/') {
        @Override
        public boolean prioryThen(char symbol) {
            return symbol == DIVIDE.symbol || symbol == MULTIPLY.symbol;
        }

        @Override
        public PostfixProcessor process(Stack<PostfixProcessor> stack) {
            PostfixProcessor right = stack.pop();
            PostfixProcessor left = stack.pop();
            return PostfixProcessor.divide(left, right);
        }
    };

    private char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    public static Operator symbolOf(char symbol) {
        if (symbol == PLUS.symbol) return PLUS;
        if (symbol == MINUS.symbol) return MINUS;
        if (symbol == MULTIPLY.symbol) return MULTIPLY;
        if (symbol == DIVIDE.symbol) return DIVIDE;
        return null;
    }

    public static List<Character> symbols() {
        return Arrays.asList(PLUS.symbol, MINUS.symbol, MULTIPLY.symbol, DIVIDE.symbol);
    }

    public abstract boolean prioryThen(char symbol);

    public abstract PostfixProcessor process(Stack<PostfixProcessor> stack);
}
