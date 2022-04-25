package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.io.command.AddPlayerCommand;
import it.eparlato.goosegame.io.command.ApplicationCommand;
import it.eparlato.goosegame.io.command.ExitCommand;
import it.eparlato.goosegame.io.command.NullCommand;

public class CommandParser {
    public ApplicationCommand buildCommandFromInput(String userInput) {
        if ("exit".equals(userInput)) {
            return new ExitCommand();
        }

        if (userInput.startsWith("add player ")) {
           String playerName = userInput.split("add player ")[1];
           return new AddPlayerCommand(playerName);
        }

        return new NullCommand();
    }
}
