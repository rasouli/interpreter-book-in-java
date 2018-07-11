package com.github.rasouli.jmonkey.ast;

import java.util.List;

public  interface Parser {

    void nextToken();
    Program parseProgram();
    List<String> errors();
}
