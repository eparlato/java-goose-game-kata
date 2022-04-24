package it.eparlato.goosegame;

import java.util.Objects;

public class AddPlayerCommand implements  ApplicationCommand {
    private final String playerName;

    public AddPlayerCommand(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void executeOn(Application application) {
        application.addPlayerWithName(playerName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddPlayerCommand that = (AddPlayerCommand) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
