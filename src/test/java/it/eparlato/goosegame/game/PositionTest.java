package it.eparlato.goosegame.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @Test
    void increments_itself() {
        Position position = new Position(9);

        assertThat(position.add(3)).isEqualTo(new Position(12));
    }

    @Test
    void returns_Start_as_tagValue_when_value_is_zero() {
        Position position = new Position(0);

        assertThat(position.tagValue()).isEqualTo("Start");
    }

    @Test
    void returns_its_value_as_tagValue() {
        Position position = new Position(1);

        assertThat(position.tagValue()).isEqualTo("1");
    }
}