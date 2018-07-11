package com.github.rasouli.jmonkey.ast;

import com.github.rasouli.jmonkey.lexer.Lexer;
import com.github.rasouli.jmonkey.tokens.Token;
import com.github.rasouli.jmonkey.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public class BasicParser implements Parser {

    Lexer lexer;
    Token currentToken;
    Token peekToken;
    List<String> errors = new ArrayList<>();

    public Token getPeekToken() {
        return peekToken;
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public Lexer getLexer() {
        return lexer;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public void nextToken() {
        this.currentToken = this.peekToken;
        this.peekToken = lexer.nextToken();
    }

    @Override
    public Program parseProgram() {

        Program program = new Program();

        while (currentToken.getType() != TokenType.EOF) {
            Statement statement = parseStatement();
            ArrayList<Statement> curStatements = program.getStatements();
            curStatements.add(statement);
            program.setStatements(curStatements);

            nextToken();
        }

        return program;
    }

    private Statement parseStatement() {
        switch (currentToken.getType()) {
            case LET:
                return parseLetStatement();
        }

        return null;
    }

    private LetStatement parseLetStatement() {
        LetStatement letStatement = new LetStatement();
        letStatement.setToken(currentToken); // current token is LET type with literal value of "let"

        if (!expectPeek(TokenType.IDENT)) { // I expect an identifier after let
            return null;
        }


        // we have identifier as current token.
        Identifier identifier = new Identifier(); // create an identifier expression, for Name part of the let statement
        identifier.setToken(currentToken);
        identifier.setValue(currentToken.literal());
        letStatement.setName(identifier);

        // if there is no assign right after the identifier, then this is not a valid let statement!
        if (!expectPeek(TokenType.ASSIGN)) {
            return null;
        }

        //TODO: skipping expression for now

        while (!currentTokenIs(TokenType.SEMICOLON)) {
            nextToken();
        }

        return letStatement;

    }

    private boolean expectPeek (TokenType t) {
        if (peekTokenIs(t)){
            nextToken();
            return true;
        } else {
            peekError(t);
            return false;
        }
    }
    private boolean peekTokenIs(TokenType type) {
        return peekToken.getType() == type;
    }

    private boolean currentTokenIs(TokenType type) {
        return currentToken.getType() == type;
    }

    private void peekError(TokenType t) {
        String error = String.format("expected next token to be of type %s, but got %s instead with literal %s", t, peekToken.getType(), peekToken.literal());
        this.errors.add(error);
    }


    public List<String> errors() {
        return this.errors;
    }
}
