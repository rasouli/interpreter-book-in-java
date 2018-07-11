package com.github.rasouli.jmonkey.lexer;

import com.github.rasouli.jmonkey.tokens.Token;

import java.util.Iterator;

public interface Lexer extends Iterator<Token> {
    Token nextToken();
}
