package it.eparlato.goosegame.game;

import java.util.Objects;

public class Position {
    private final int value;

    public Position(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public Position add(int valueToAdd) {
        return new Position(this.value + valueToAdd);
    }

    public Position sub(int valueToSub) {
        return new Position(this.value - valueToSub);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Position{" +
                "value=" + value +
                '}';
    }
}
