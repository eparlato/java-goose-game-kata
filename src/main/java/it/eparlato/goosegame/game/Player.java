package it.eparlato.goosegame.game;

import java.util.Objects;

public class Player {
    private final String playerName;
    private Position previousPosition = new Position(0);
    private Position currentPosition = new Position(0);

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String name() {
        return playerName;
    }

    public void increasePositionBy(int value) {
        previousPosition = new Position(currentPosition.value());

        currentPosition = currentPosition.add(value);
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
