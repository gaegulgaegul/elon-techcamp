package com.elonsoft.domain.postfix;

import com.elonsoft.domain.postfix.PostfixParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostfixParserTest {

    @ParameterizedTest
    @CsvSource({"0+1,01+", "(0+1)*3,01+3*"})
    @DisplayName("중위 표현식을 후위표현식으로 변환")
    void infix_to_postfix(String infix, String actual) {
        PostfixParser postfixParser = new PostfixParser();
        String postfix = postfixParser.parse(infix);
        assertEquals(postfix, actual);
    }
}