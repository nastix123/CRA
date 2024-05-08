package org.example.lexer;

import java.util.ArrayList;
import java.util.List;

class Scanner {
    private final String data;
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
//        switch (symbol) {
//            case '(': TypeOfToken.LEFT_BRACE; break;
//
//        }
    }
    private char advance() {
        current++;
        return data.charAt(current - 1);
    }
}
