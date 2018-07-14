package com.github.rasouli.jmonkey.ast;

import java.util.ArrayList;

public class Program extends Node {
    private ArrayList<Statement> statements = new ArrayList<>();

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public String getTokenLiteral() {
        if (statements.size() > 0){
            return statements.get(0).getTokenLiteral();
        }

        return "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        statements.forEach(s -> {
            sb.append(s.toString());
        });

        return sb.toString();
    }


}
