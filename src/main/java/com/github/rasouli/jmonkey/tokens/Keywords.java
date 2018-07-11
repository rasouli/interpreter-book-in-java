package com.github.rasouli.jmonkey.tokens;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Keywords {
    private static Map<String, TokenType> literalToTokenTypeMapping = new HashMap<String, TokenType>();
    static {
        literalToTokenTypeMapping.put("function", TokenType.FUNCTION);
        literalToTokenTypeMapping.put("let", TokenType.LET);
        literalToTokenTypeMapping.put("true", TokenType.TRUE);
        literalToTokenTypeMapping.put("false", TokenType.FALSE);
        literalToTokenTypeMapping.put("if", TokenType.IF);
        literalToTokenTypeMapping.put("else", TokenType.ELSE);
        literalToTokenTypeMapping.put("return", TokenType.RETURN);

    }

    public static TokenType lookupIdentifier(String literal) {
        return literalToTokenTypeMapping.getOrDefault(literal, TokenType.IDENT);
    }
}
