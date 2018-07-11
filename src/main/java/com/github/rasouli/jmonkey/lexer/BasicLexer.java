package com.github.rasouli.jmonkey.lexer;

import com.github.rasouli.jmonkey.tokens.Keywords;
import com.github.rasouli.jmonkey.tokens.Token;
import com.github.rasouli.jmonkey.tokens.TokenType;
import com.github.rasouli.jmonkey.tokens.Tokens;

import java.util.Iterator;
import java.util.stream.Stream;

class BasicLexer implements Lexer, Iterator<Token> {

    String input;

    // current position in input
    int position = 0;

    // current reading position
    int readPosition = 0;

    // current char under examination
    char currentChar;


    BasicLexer(String input) {
        this.input = input;
        readChar();
    }

    public Token nextToken() {
        Token token = Tokens.empty();
        skipWhitespace();

        switch (this.currentChar) {
            case '=':
                switch (peekChar()) {
                    case '=':
                        char curChar = currentChar;
                        readChar();
                        char nextChar = currentChar;

                        token = newToken(TokenType.EQ, String.valueOf(new char[]{curChar, nextChar}));
                        break;
                    default:
                        token = newToken(TokenType.ASSIGN, this.currentChar);
                }
                break;
            case ';':
                token = newToken(TokenType.SEMICOLON, this.currentChar);
                break;
            case '(':
                token = newToken(TokenType.LPAREN, this.currentChar);
                break;
            case ')':
                token = newToken(TokenType.RPAREN, this.currentChar);
                break;
            case '{':
                token = newToken(TokenType.LBRACE, this.currentChar);
                break;
            case '}':
                token = newToken(TokenType.RBRACE, this.currentChar);
                break;
            case '+':
                token = newToken(TokenType.PLUS, this.currentChar);
                break;
            case ',':
                token = newToken(TokenType.COMMA, this.currentChar);
                break;
            case 0:
                token = newToken(TokenType.EOF, '\0');
                break;
            case '-':
                token = newToken(TokenType.MINUS, this.currentChar);
                break;
            case '!':
                switch (peekChar()) {
                    case '=':
                        char curCh = currentChar;
                        readChar();
                        char nextChar = currentChar;
                        token = newToken(TokenType.NOT_EQ, String.valueOf(new char[]{curCh, nextChar}));
                        break;
                    default:
                        token = newToken(TokenType.BANG, currentChar);
                        break;
                }
                break;
            case '/':
                token = newToken(TokenType.SLASH, this.currentChar);
                break;
            case '\\':
                token = newToken(TokenType.BACKSLASH, this.currentChar);
                break;
            case '>':
                token = newToken(TokenType.GT, this.currentChar);
                break;
            case '<':
                token = newToken(TokenType.LT, this.currentChar);
                break;
            case '*':
                token = newToken(TokenType.ASTERISK, this.currentChar);
                break;
            default:

                if (isLetter(this.currentChar)) {
                    String candidateIdentifier = readIdentifier();
                    token = newToken(
                            Keywords.lookupIdentifier(candidateIdentifier),
                            candidateIdentifier
                    );
                    return token; // we return early here because job for reading this identifier token is done.
                } else if (isDigit(this.currentChar)) {
                    String number = readNumber();
                    token = newToken(TokenType.INT, number);
                    return token;
                } else {
                    token = Tokens.error();
                }

        }

        readChar();
        return token;
    }

    private char peekChar() {
        if (this.readPosition >= input.length()) {
            return '\0';
        } else {
            return this.input.charAt(this.readPosition);
        }
    }

    private void readChar() {

        if (this.readPosition >= input.length()) {
            this.currentChar = 0;
        } else {
            this.currentChar = this.input.charAt(this.readPosition);
        }
        this.position = this.readPosition;
        this.readPosition++;
    }

    private Token newToken(TokenType type, char ch) {
        return Tokens.newToken(type, String.valueOf(ch));
    }

    private Token newToken(TokenType type, String literal) {
        return Tokens.newToken(type, literal);
    }

    private boolean isLetter(char ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_';
    }

    private String readIdentifier() {
        int beginingOfIdentifier = position;
        while (isLetter(currentChar)) {
            readChar();
        }

        return input.substring(beginingOfIdentifier, position); // not readPosition, because it will make us read extra character.
    }

    private void skipWhitespace() {
        while (currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r') {
            readChar();
        }
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private String readNumber() {
        int currentPosition = position;
        while (isDigit(currentChar)) {
            readChar();
        }

        return input.substring(currentPosition, position);
    }

    public String getInput() {
        return input;
    }

    public int getPosition() {
        return position;
    }

    public int getReadPosition() {
        return readPosition;
    }

    public char getCurrentChar() {
        return currentChar;
    }


    @Override
    public boolean hasNext() {
        if (readPosition >= input.length()){
            return false;
        }

        char nextChar = input.charAt(readPosition);
        return nextChar != '\0';
    }

    @Override
    public Token next() {
        return nextToken();
    }
}
