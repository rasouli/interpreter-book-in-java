package com.github.rasouli.jmonkey.ast;

public class ReturnStatement extends Statement {

    Expression returnValue;

    public Expression getReturnValue(){
        return this.returnValue;
    }

    public void setReturnValue(Expression returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String getTokenLiteral() {
        return this.getToken().getLiteral();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s ", this.getTokenLiteral()));
        if (returnValue != null){
            sb.append( returnValue.toString());
        }
        sb.append(";");
        return sb.toString();
    }
}
