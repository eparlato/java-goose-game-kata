package it.eparlato.goosegame.game;

import java.util.Objects;

public class Player {
    private static final int WINNING_POSITION_VALUE = 63;
    private final String playerName;
    private Position previousPosition = new Position(0);
    private Position currentPosition = new Position(0);
    private boolean hasBounced = false;


    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String name() {
        return playerName;
    }

    public void increasePositionBy(int value) {
        resetBouncesFlag();
        previousPosition = new Position(currentPosition.value());

        currentPosition = currentPosition.add(value);

        if(currentPosition.value() > WINNING_POSITION_VALUE) {
            hasBounced = true;
            int numberOfPositionsToGoBack = currentPosition.value() - WINNING_POSITION_VALUE;
            currentPosition = new Position(WINNING_POSITION_VALUE - numberOfPositionsToGoBack);
        }
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

    public boolean isOnWinningPosition() {
        return currentPosition.value() == WINNING_POSITION_VALUE;
    }

    public boolean hasBounced() {
        return hasBounced;
    }

    private void resetBouncesFlag() {
        hasBounced = false;
    }
}
