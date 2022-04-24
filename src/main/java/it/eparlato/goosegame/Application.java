package it.eparlato.goosegame;

public class Application {
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private final CommandParser commandParser;
    private final GooseGame gooseGame;
    boolean running = true;

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
        }

        consoleOutput.show("Bye.");
    }

    public void quit() {
        running = false;
    }

    public GooseGame getGooseGame() {
        return gooseGame;
    }

    public void addPlayerWithName(String playerName) {
        String response = gooseGame.addPlayer(new Player(playerName));
        consoleOutput.show(response);
    }
}
