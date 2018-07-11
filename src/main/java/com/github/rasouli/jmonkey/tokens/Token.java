package com.github.rasouli.jmonkey.tokens;

public class Token {
    TokenType type;
    String literal;

     Token(TokenType type, String literal) {
        this.type = type;
        this.literal = literal;

    }

    public TokenType getType() {
        return this.type;
    }

    public String literal() {
        return this.literal;
    }

}
