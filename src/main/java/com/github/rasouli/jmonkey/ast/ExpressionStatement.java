package com.github.rasouli.jmonkey.ast;

public class ExpressionStatement extends Statement {

    private Expression expression;

    @Override
    public String getTokenLiteral() {
        return getToken().getLiteral();
    }


    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }


    @Override
    public String toString() {
        if (this.expression != null) {
            return expression.toString();
        }

        return "";
    }

}
