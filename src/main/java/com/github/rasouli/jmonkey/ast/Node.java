package com.github.rasouli.jmonkey.ast;

import com.github.rasouli.jmonkey.tokens.Token;

public abstract class Node {
    private Token token;

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    abstract String tokenLiteral();

}

