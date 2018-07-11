package com.github.rasouli.jmonkey.ast;

import com.github.rasouli.jmonkey.lexer.Lexer;

public class Parsers {
    public static Parser basic(Lexer lexer){
        BasicParser p = new BasicParser();
        p.setLexer(lexer);

        //read two tokens to set curToken and peekToken
        p.nextToken();
        p.nextToken();

        return p;
    }
}
