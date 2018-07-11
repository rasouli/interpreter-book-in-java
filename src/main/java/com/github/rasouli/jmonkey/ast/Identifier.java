package com.github.rasouli.jmonkey.ast;

public class Identifier extends Expression{

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String tokenLiteral() {
        return getToken().literal();
    }
}
