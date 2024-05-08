package org.example.lexer;

public enum TypeOfToken {

    //KEYWORDS
    IF,
    ELSE,
    PRINT,
    RETURN,
    BREAK,
    TRUE,
    FALSE,
    FUN,
    VAR,

    //SINGLE AND TWO CHARACTERS TOKENS
    MINUS,
    PLUS,
    MULTIPLICATION,
    DIVIDE,
    COMMA,
    COLON,
    SEMICOLON,
    LEFT_BRACE,
    RIGHT_BRACE,
    EQUAL, EQUAL_EQUAL,


    //LITERALS
    IDENTIFIER,
    STRING,
    NUMBER

}
