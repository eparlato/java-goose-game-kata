package it.eparlato.goosegame;

public class Application {
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private CommandParser commandParser;
    boolean running = true;

    public Application(ConsoleInput consoleInput, ConsoleOutput consoleOutput, CommandParser commandParser) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
        this.commandParser = commandParser;
    }

    public void run() {

        while(running) {
            String userInput = consoleInput.getUserInput();
            ApplicationCommand command = commandParser.getCommandFor(userInput);
            command.executeOn(this);
        }

        consoleOutput.show("Bye.");
    }

    public void quit() {
        running = false;
    }
}
