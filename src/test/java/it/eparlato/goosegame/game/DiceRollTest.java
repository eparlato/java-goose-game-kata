package it.eparlato.goosegame.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiceRollTest {
    @Test
    void sums_the_value_of_its_dice() {
        DiceRoll roll = new DiceRoll(4,6);

        assertThat(roll.sumOfDiceValues()).isEqualTo(10);
    }
}