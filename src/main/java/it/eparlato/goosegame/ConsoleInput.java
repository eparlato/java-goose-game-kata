package it.eparlato.goosegame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public String getUserInput() {
        return scanner.next();
    }
}
