package org.example.lexer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Scanner {
    private String data;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String data) {
        this.data = data;
    }

    List<Token> getTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }
        return this.tokens;
    }

    private boolean isAtEnd() {
        return current >= data.length();
    }

    private void scanToken() {
        char symbol = advance();
        switch (symbol) {
            case '(': addToken(TypeOfToken.LEFT_BRACE); break;
            case ')': addToken(TypeOfToken.RIGHT_BRACE); break;
            case '{': addToken(TypeOfToken.LEFT_CURL_BRACE); break;
            case '}': addToken(TypeOfToken.RIGHT_CURL_BRACE); break;
            case '+':addToken(TypeOfToken.PLUS); break;
            case '-':addToken(TypeOfToken.MINUS); break;
            case '*':addToken(TypeOfToken.MULTIPLICATION); break;
            case '/':addToken(TypeOfToken.DIVIDE); break;
            case ';':addToken(TypeOfToken.SEMICOLON); break;
            case ',':addToken(TypeOfToken.COMMA); break;
            case ':':addToken(TypeOfToken.COLON); break;

            case '!':
                addToken(match('=')?TypeOfToken.NOT_EQUAL: TypeOfToken.EXCL); break;
            case '=':
                addToken(match('=')?TypeOfToken.EQUAL_EQUAL: TypeOfToken.EQUAL); break;
            case '<':
                addToken(match('=')?TypeOfToken.LESS_THAN_EQUAL: TypeOfToken.LESS_THAN); break;
            case '>':
                addToken(match('=')?TypeOfToken.GREATER_THAN_EQUAL: TypeOfToken.GREATER_THAN); break;
            case '\t':break;
            case '\n':
                line++;
                break;

            default:

                if (isDigit(symbol)) {
                    number();
                } else if (isAlpha(symbol)) {
                    identifier();
                }
                CRA.error(line, "Unexpected character: " + symbol);
                break;
        }
    }
    private char advance() {
        current++;
        return data.charAt(current - 1);
    }

    private void addToken(TypeOfToken type) {
        addToken(type, null);
    }

    private void addToken(
            TypeOfToken type,
            Object literal
    ) {
        String text = data.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private boolean match(char exp) {
        if (isAtEnd()) return false;
        if (data.charAt(current) != exp) return false;

        current++;
        return true;
    }


    private void string() {
        while (peek() != '"' && isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd()) {
            CRA.error(line, "Unexpected end of string");
            return;
        }

        advance();

        String value = data.substring(start+1, current-1);
        addToken(TypeOfToken.STRING, value);
     }

    private char peek() {
        if (isAtEnd()) return '\0';
        return data.charAt(current);
    }

    private boolean isDigit(char digit) {
        return digit >= '0' && digit <= '9';
    }

    private void number() {
        while (isDigit(peek())) advance();

        if ((peek() == ',' || (peek() == '.' && isDigit(peekNext())))) {
            advance();
            while (isDigit(peek())) advance();
        }
        addToken(TypeOfToken.NUMBER, Double.parseDouble(data.substring(start, current)));
    }


    private char peekNext() {
        if (current +1 >= data.length()) return '\0';
        return data.charAt(current+1);
    }


    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c=='_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = data.substring(start,current);
        TypeOfToken type = keywords.get(text);
        if (type == null) {
            type = TypeOfToken.IDENTIFIER;
        }
        addToken(type);
    }

    private static final Map<String, TypeOfToken> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("cap", TypeOfToken.FALSE);
        keywords.put("noCap", TypeOfToken.TRUE);
        keywords.put("побазарим", TypeOfToken.PRINT);
        keywords.put("эщэщэщ", TypeOfToken.VAR);
        keywords.put("ебашь", TypeOfToken.FUN);
    }






}
