package org.example.representation;

import org.example.lexer.Token;

public abstract class Expression {

    public interface Visitor<R> {
        R visitBinaryExpr(BinaryExpression expr);

        R visitGroupingExpr(GroupingExpression expr);

        R visitLiteralExpr(LiteralExpression expr);

        R visitUnaryExpr(UnaryExpression expr);
    }

    abstract <R> R accept(Visitor<R> visitor);

    public static class BinaryExpression extends Expression {
        public BinaryExpression(
                Expression left,
                Token op,
                Expression right
        ) {
            this.left = left;
            this.op = op;
            this.right = right;
        }

        final Expression left;
        final Token op;
        final Expression right;


        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpr(this);
        }
    }

   public static class UnaryExpression extends Expression {
        UnaryExpression(
                Expression right,
                Token op
        ) {
            this.right = right;
            this.op = op;
        }

        final Expression right;
        final Token op;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitUnaryExpr(this);
        }
    }


    public static class LiteralExpression extends Expression {
        public LiteralExpression(
                Object literal
        ) {
            this.literal = literal;
        }

        final Object literal;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }
    }


    public static class GroupingExpression extends Expression {
        public GroupingExpression(Expression expr) {
            this.expression = expr;
        }

        final Expression expression;

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGroupingExpr(this);
        }
    }
}
