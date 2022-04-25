package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.Application;
import it.eparlato.goosegame.game.DiceRoll;

import java.util.Objects;

public class MovePlayerCommand implements ApplicationCommand {
    private final String playerName;
    private final int firstDiceValue;
    private final int secondDiceValue;

    public MovePlayerCommand(String playerName, int firstDiceValue, int secondDiceValue) {
        this.playerName = playerName;
        this.firstDiceValue = firstDiceValue;
        this.secondDiceValue = secondDiceValue;
    }

    @Override
    public void executeOn(Application application) {
        application.movePlayer(playerName, new DiceRoll(firstDiceValue, secondDiceValue));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovePlayerCommand that = (MovePlayerCommand) o;
        return firstDiceValue == that.firstDiceValue && secondDiceValue == that.secondDiceValue && Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, firstDiceValue, secondDiceValue);
    }
}
