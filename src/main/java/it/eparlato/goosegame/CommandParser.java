package it.eparlato.goosegame;

public class CommandParser {
    public ApplicationCommand getCommandFor(String userInput) {
        if("exit".equals(userInput)) {
            return new ExitCommand();
        }

        return null;
    }
}
