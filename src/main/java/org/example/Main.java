package org.example;
import org.example.lexer.Token;
import org.example.lexer.TypeOfToken;
import org.example.representation.*;

public class Main {
    public static void main(String[] args) {

        Expression expression = new Expression.BinaryExpression(
                new Expression.GroupingExpression(
                        new Expression.BinaryExpression(
                                new Expression.LiteralExpression(1),
                                new Token(TypeOfToken.PLUS, "+", null, 1),
                                new Expression.LiteralExpression(2)
                        )
                ),
                new Token(TypeOfToken.MULTIPLICATION, "*", null, 1),
                new Expression.GroupingExpression(
                        new Expression.BinaryExpression(
                                new Expression.LiteralExpression(10),
                                new Token(TypeOfToken.MINUS, "-", null, 1),
                                new Expression.LiteralExpression(2)
                        )
                )
        );

        ASTPrinter printer = new ASTPrinter();
        String result = printer.print(expression);
        System.out.println(result);
    }
}

