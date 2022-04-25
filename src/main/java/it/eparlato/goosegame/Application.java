package it.eparlato.goosegame;

import it.eparlato.goosegame.game.DiceRoll;
import it.eparlato.goosegame.game.GooseGame;
import it.eparlato.goosegame.game.MessageResponseFactory;
import it.eparlato.goosegame.io.ConsoleInput;
import it.eparlato.goosegame.io.ConsoleOutput;
import it.eparlato.goosegame.io.command.ApplicationCommand;
import it.eparlato.goosegame.io.command.CommandParser;

public class Application {
    public static final String EXIT_MESSAGE = "Bye.";
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private final CommandParser commandParser;
    private final GooseGame gooseGame;
    private boolean running = true;

    public Application(ConsoleInput consoleInput, ConsoleOutput consoleOutput, CommandParser commandParser,
                       GooseGame gooseGame) {
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
    }

    public void quit() {
        running = false;
        consoleOutput.show(EXIT_MESSAGE);
    }

    public GooseGame getGooseGame() {
        return gooseGame;
    }

    public void addPlayer(String playerName) {
        String response = gooseGame.addPlayer(playerName);
        consoleOutput.show(response);
    }

    public void movePlayer(String playerName, DiceRoll diceRoll) {
        String response = gooseGame.movePlayer(playerName, diceRoll);
        consoleOutput.show(response);

        if (gooseGame.isOver()) {
            quit();
        }
    }

    public static void main(String[] args) {
        Application application = new Application(new ConsoleInput(System.in), new ConsoleOutput(System.out),
                new CommandParser(), new GooseGame(new MessageResponseFactory()));

        application.run();
    }

}
