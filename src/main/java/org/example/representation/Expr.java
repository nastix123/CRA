package org.example.representation;

import org.example.lexer.Token;

abstract class Expr {
    static class BinaryExpr extends Expr {
        BinaryExpr(
                Expr left,
                Token op,
                Expr right
        ) {
            this.left = left;
            this.op = op;
            this.right = right;
        }
        final Expr left;
        final Token op;
        final Expr right;
    }
}
