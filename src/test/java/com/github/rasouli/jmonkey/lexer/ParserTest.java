package com.github.rasouli.jmonkey.lexer;

import com.github.rasouli.jmonkey.ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

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
                .map(ls -> new String[]{ls.tokenLiteral(), ls.getName().getValue(), ls.getName().tokenLiteral()})
                .collect(Collectors.toList());


//        assertEquals(outCome, expectations);
        assertThat(outCome).containsAll(expectations);
        assertThat(p.errors()).size().isEqualTo(0);
    }
}
