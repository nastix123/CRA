package org.example.lexer;

record Token(TypeOfToken type, String lexeme, Object literal, int line) {

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}
