package it.eparlato.goosegame;

public class Application {
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private final CommandParser commandParser;
    private final GooseGame gooseGame;
    boolean running = true;
    private String gooseGameResponse = "";

    public Application(ConsoleInput consoleInput, ConsoleOutput consoleOutput, CommandParser commandParser, GooseGame gooseGame) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
        this.commandParser = commandParser;
        this.gooseGame = gooseGame;
    }

    public void run() {

        while(running) {
            String userInput = consoleInput.getUserInput();
            ApplicationCommand command = commandParser.buildCommandFromInput(userInput);

            command.executeOn(this);

            consoleOutput.show(gooseGameResponse);
        }

        consoleOutput.show("Bye.");
    }

    public void quit() {
        running = false;
    }

    public GooseGame getGooseGame() {
        return gooseGame;
    }

    public void setGooseGameResponse(String response) {
        this.gooseGameResponse = response;
    }
}
