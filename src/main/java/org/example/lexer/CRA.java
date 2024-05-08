package org.example.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CRA {
    public static void main(String[] args) throws IOException {
        if(args.length >1) {
            System.out.println("Usage: cra");
            System.exit(64);
        } else if (args.length == 1) {

        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));

    }

    private static void runPromt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);


        for (;;) {
            System.out.println("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
        }
    }


    private static void run(String line) {
        org.example.lexer.Scanner scanner = new Scanner(line);
        List<Token> tokens = scanner.getTokens();
    }
}
