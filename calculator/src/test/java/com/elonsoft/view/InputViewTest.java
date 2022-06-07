package com.elonsoft.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static java.lang.System.setIn;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputViewTest {

    @Test
    @DisplayName("[성공] 문자열 입력")
    void given_text_when_input_console_then_ok() {
        String userInput = "그냥아무문자열이나입력";
        setIn(new ByteArrayInputStream(userInput.getBytes()));

        String inputExpression = "그냥아무문자열이나입력";
        String expression = InputView.expression();
        assertEquals(inputExpression, expression);
    }
}