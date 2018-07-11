package com.github.rasouli.jmonkey.tokens;

public class Tokens {
    public static Token newToken(TokenType type, String literal){
        return new Token(type, literal);
    }

    public static Token empty(){
        return new Token(TokenType.EOF, "");
    }

    public static Token error(){
        return new Token(TokenType.ILLEGAL, "");
    }
}
