package it.eparlato.goosegame.io.command;

public class CommandParser {
    public ApplicationCommand buildCommandFromInput(String userInput) {
        if ("exit".equals(userInput)) {
            return buildExitCommand();
        }

        if (userInput.startsWith("add player ")) {
            return buildAddPlayerCommand(userInput);
        }

        if (userInput.startsWith("move ")) {
            return buildMovePlayerCommand(userInput);
        }

        return new NullCommand();
    }

    private ExitCommand buildExitCommand() {
        return new ExitCommand();
    }

    private AddPlayerCommand buildAddPlayerCommand(String userInput) {
        String playerName = userInput.split("add player ")[1];
        return new AddPlayerCommand(playerName);
    }

    private MovePlayerCommand buildMovePlayerCommand(String userInput) {
        String[] args = userInput.split(" ");

        String playerName = args[1];

        int firstDiceValue = Integer.parseInt(args[2].replace(",", ""));
        int secondDiceValue = Integer.parseInt(args[3]);

        return new MovePlayerCommand(playerName, firstDiceValue, secondDiceValue);
    }
}
