package com.github.rasouli.jmonkey.tokens;

public enum TokenType {

    ILLEGAL("ILLEGAL"),
    EOF("EOF"),
    IDENT("IDENT"),
    INT("INT"),
    ASSIGN("ASSIGN"),
    MINUS("-"),
    BANG("!"),
    ASTERISK("*"),
    SLASH("/"),
    BACKSLASH("\\"),
    PLUS("PLUS"),
    COMMA(","),
    SEMICOLON(";"),
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    FUNCTION("FUNCTION"),
    LET("LET"),
    LT("<"),
    GT(">"),
    TRUE("true"),
    FALSE("false"),
    IF("if"),
    ELSE("else"),
    RETURN("return"),
    EQ("=="),
    NOT_EQ("!=");


    private String type;

    TokenType(String type) {
        this.type = type;
    }
}
