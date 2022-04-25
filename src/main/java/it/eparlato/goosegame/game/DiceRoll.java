package it.eparlato.goosegame.game;

import java.util.Objects;

public class DiceRoll {
    private final int firstDiceValue;
    private final int secondDiceValue;

    public DiceRoll(int firstDiceValue, int secondDiceValue) {
        this.firstDiceValue = firstDiceValue;
        this.secondDiceValue = secondDiceValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceRoll diceRoll = (DiceRoll) o;
        return firstDiceValue == diceRoll.firstDiceValue && secondDiceValue == diceRoll.secondDiceValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstDiceValue, secondDiceValue);
    }
}
