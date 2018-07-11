package com.github.rasouli.jmonkey.ast;

import com.github.rasouli.jmonkey.tokens.Token;

public class LetStatement extends Statement {
    // let identifier = expression

    /**
     *  why identifier is a Expression?
     * To hold the identifer of the binding, the x in let x = 5;, we have the Identifier struct type,
     * which implements the Expression interface. But the identifer in a let statement doesn’t produce
     * a value, right? So why is it an Expression? It’s to keep things simple. Identifers in other parts
     * of a Monkey program do produce values, e.g.: let x = valueProducingIdentifier;
     */
    private Identifier name;
    private Expression value;

    public Identifier getName() {
        return name;
    }

    public void setName(Identifier identifier){
        this.name = identifier;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public String tokenLiteral() {
        return this.getToken().literal();
    }
}
