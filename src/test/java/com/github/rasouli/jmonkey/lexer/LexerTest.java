package com.github.rasouli.jmonkey.lexer;

import com.github.rasouli.jmonkey.tokens.Token;
import com.github.rasouli.jmonkey.tokens.TokenType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.*;

public class LexerTest {

    @Test
    public void testNextTokenWithOperatorsPunchuationsAndParenthesesAndBrackets() {

        String input = "=+(){},;";
        List<Object[]> expectations = Arrays.asList(new Object[][]{
                {"=", TokenType.ASSIGN},
                {"+", TokenType.PLUS},
                {"(", TokenType.LPAREN},
                {")", TokenType.RPAREN},
                {"{", TokenType.LBRACE},
                {"}", TokenType.RBRACE},
                {",", TokenType.COMMA},
                {";", TokenType.SEMICOLON}
        });

        Lexer lexer = Lexers.basic(input);

        for (Object[] expectation : expectations) {
            String literal = (String) expectation[0];
            TokenType type = (TokenType) expectation[1];

            Token token = lexer.nextToken();
            assertEquals(literal, token.getLiteral());
            assertEquals(type, token.getType());

        }


    }

    @Test
    public void testNextCodeWithVariableAssignAndFunctionDefinition() {
        String input = "let five = 5; " +
                "let ten = 10; " +
                "let add = function(x, y) { " +
                "x + y;  " +
                "};" +
                "let result = add(five, ten);  " +
                "!-/*5;  " +
                "5 < 10 > 5;" +
                "if (5 < 10) {" +
                "  return true;" +
                "} else {" +
                "  return false;" +
                "}" +
                "10 == 10;" +
                "10 != 9;";

        List<Object[]> expectations = Arrays.asList(new Object[][]{
                {"let", TokenType.LET},
                {"five", TokenType.IDENT},
                {"=", TokenType.ASSIGN},
                {"5", TokenType.INT},
                {";", TokenType.SEMICOLON},
                {"let", TokenType.LET},
                {"ten", TokenType.IDENT},
                {"=", TokenType.ASSIGN},
                {"10", TokenType.INT},
                {";", TokenType.SEMICOLON},
                {"let", TokenType.LET},
                {"add", TokenType.IDENT},
                {"=", TokenType.ASSIGN},
                {"function", TokenType.FUNCTION},
                {"(", TokenType.LPAREN},
                {"x", TokenType.IDENT},
                {",", TokenType.COMMA},
                {"y", TokenType.IDENT},
                {")", TokenType.RPAREN},
                {"{", TokenType.LBRACE},
                {"x", TokenType.IDENT},
                {"+", TokenType.PLUS},
                {"y", TokenType.IDENT},
                {";", TokenType.SEMICOLON},
                {"}", TokenType.RBRACE},
                {";", TokenType.SEMICOLON},
                {"let", TokenType.LET},
                {"result", TokenType.IDENT},
                {"=", TokenType.ASSIGN},
                {"add", TokenType.IDENT},
                {"(", TokenType.LPAREN},
                {"five", TokenType.IDENT},
                {",", TokenType.COMMA},
                {"ten", TokenType.IDENT},
                {")", TokenType.RPAREN},
                {";", TokenType.SEMICOLON},
                {"!", TokenType.BANG},
                {"-", TokenType.MINUS},
                {"/", TokenType.SLASH},
                {"*", TokenType.ASTERISK},
                {"5", TokenType.INT},
                {";", TokenType.SEMICOLON},
                {"5", TokenType.INT},
                {"<", TokenType.LT},
                {"10", TokenType.INT},
                {">", TokenType.GT},
                {"5", TokenType.INT},
                {";", TokenType.SEMICOLON},
                {"if", TokenType.IF},
                {"(", TokenType.LPAREN},
                {"5", TokenType.INT},
                {"<", TokenType.LT},
                {"10", TokenType.INT},
                {")", TokenType.RPAREN},
                {"{", TokenType.LBRACE},
                {"return", TokenType.RETURN},
                {"true", TokenType.TRUE},
                {";", TokenType.SEMICOLON},
                {"}", TokenType.RBRACE},
                {"else", TokenType.ELSE},
                {"{", TokenType.LBRACE},
                {"return", TokenType.RETURN},
                {"false", TokenType.FALSE},
                {";", TokenType.SEMICOLON},
                {"}", TokenType.RBRACE},
                {"10", TokenType.INT},
                {"==", TokenType.EQ},
                {"10", TokenType.INT},
                {";", TokenType.SEMICOLON},
                {"10", TokenType.INT},
                {"!=", TokenType.NOT_EQ},
                {"9", TokenType.INT},
                {";", TokenType.SEMICOLON}
        });

        Lexer lexer = Lexers.basic(input);

        for (Object[] expectation : expectations) {
            String literal = (String) expectation[0];
            TokenType type = (TokenType) expectation[1];

            Token token = lexer.nextToken();
            assertEquals(literal, token.getLiteral());
            assertEquals(type, token.getType());

        }
    }
}
