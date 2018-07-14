package com.github.rasouli.jmonkey.repel;

import com.github.rasouli.jmonkey.lexer.Lexers;
import com.github.rasouli.jmonkey.tokens.Token;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {

        Repel.start(System.in)
                .map(Lexers::basic)
                .flatMap((l) -> {
                    Iterable<Token> iterable = () -> l;
                    return StreamSupport.stream(iterable.spliterator(), false);
                })
                .forEach(t -> {
                    System.out.println("type :" + t.getType());
                    System.out.println("getLiteral : " + t.getLiteral());
                });
    }
}
