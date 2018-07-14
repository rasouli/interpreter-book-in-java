package com.github.rasouli.jmonkey.ast;

import com.github.rasouli.jmonkey.tokens.Token;

public class Identifier extends Expression {

    String value;

    public Identifier() {
    }

    public Identifier(Token t, String value) {
        this.setToken(t);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getTokenLiteral() {
        return getToken().getLiteral();
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
