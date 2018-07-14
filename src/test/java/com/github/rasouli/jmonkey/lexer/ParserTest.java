package com.github.rasouli.jmonkey.lexer;

import com.github.rasouli.jmonkey.ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.rasouli.jmonkey.tokens.Token;
import com.github.rasouli.jmonkey.tokens.TokenType;
import org.junit.Test;

import javax.swing.plaf.nimbus.State;

public class ParserTest {

    @Test
    public void testLetStatements() {
        String input = "let x = 5;" +
                " let y = 10;" +
                "let foobar = 838383;";

        Lexer lexer = Lexers.basic(input);
        Parser p = Parsers.basic(lexer);

        Program program = p.parseProgram();
        assertThat(program).isNotNull();

        ArrayList<Statement> letStatements = program.getStatements();

        assertThat(letStatements)
                .isNotEmpty()
                .hasSize(3);

        List<String[]> expectations = Arrays.asList(new String[][]{
                {"let", "x", "x"},
                {"let", "y", "y"},
                {"let", "foobar", "foobar"}
        });


        List<String[]> outCome = letStatements
                .stream()
                .map(LetStatement.class::cast)
                .map(ls -> new String[]{ls.getTokenLiteral(), ls.getName().getValue(), ls.getName().getTokenLiteral()})
                .collect(Collectors.toList());


//        assertEquals(outCome, expectations);
        assertThat(outCome).containsAll(expectations);
        assertThat(p.errors()).size().isEqualTo(0);
    }

    @Test
    public void testReturnStatements() {

        String input = "return 5; " +
                "return 10;" +
                " return 993322;";

        Lexer lexer = Lexers.basic(input);
        Parser p = Parsers.basic(lexer);

        Program program = p.parseProgram();

        ArrayList<Statement> statements = program.getStatements();

        assertThat(statements).size().isEqualTo(3);

        statements.forEach(s -> {
            assertThat(s)
                    .isInstanceOf(ReturnStatement.class)
                    .hasFieldOrPropertyWithValue("tokenLiteral", "return");

        });


    }

    @Test
    public void testToString() {
        List<Statement> statements = Arrays.asList( new Statement[]{
                new LetStatement(
                        new Token(TokenType.LET, "let"),
                        new Identifier(
                                new Token(TokenType.IDENT, "myVar"),
                                "myVar"
                        ),
                        new Identifier(
                                new Token(TokenType.IDENT, "anotherVar"),
                                "anotherVar"
                        )
                )
        });

        Program p = new Program();
        p.setStatements(new ArrayList(statements));
        assertThat(p.toString())
                .isEqualTo("let myVar = anotherVar;");
    }
}
