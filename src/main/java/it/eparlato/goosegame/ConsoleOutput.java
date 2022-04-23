package it.eparlato.goosegame;

import java.io.PrintStream;

public class ConsoleOutput {
    private final PrintStream printStream;

    public ConsoleOutput(PrintStream outputStream) {
        this.printStream = outputStream;
    }

    public void show(String output) {
        printStream.print(output);
    }
}
