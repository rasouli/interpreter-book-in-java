package com.github.rasouli.jmonkey.ast;

public  interface Parser {

    void nextToken();
    Program parseProgram();
}
