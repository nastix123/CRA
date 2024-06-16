package org.example.representation;



public class ASTPrinter implements Expression.Visitor<String>{

    public String print(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expression.BinaryExpression expr) {
        return parenthesize(expr.op.lexeme(), expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expression.GroupingExpression expr) {
        return parenthesize("", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expression.LiteralExpression expr) {
        if (expr.literal ==  null) return "null";
        return expr.literal.toString();
    }

    @Override
    public String visitUnaryExpr(Expression.UnaryExpression expr) {
        return parenthesize(expr.op.lexeme(), expr.right);
    }

    private String parenthesize(String name, Expression... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expression expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}
