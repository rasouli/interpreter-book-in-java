package com.github.rasouli.jmonkey.lexer;

import com.github.rasouli.jmonkey.ast.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.not;


public class ParserTest {

    @Test
    public  void testLetStatements() {
        String input = "let x = 5;" +
                " let y = 10;" +
                "let foobar = 838383;";

        Lexer lexer = Lexers.basic(input);
        Parser p = Parsers.basic(lexer);

        Program program = p.parseProgram();
        assertNotNull("program should not be null.", program);

        ArrayList<Statement> letStatements = program.getStatements();
        assertThat("let statements should not be empty", letStatements, not(IsEmptyCollection.empty()));
        assertThat("let statements should give 3 parsed statements", letStatements, hasSize(3));

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
        assertThat(outCome, contains(expectations.toArray()));
    }
}
