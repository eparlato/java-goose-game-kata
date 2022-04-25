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
    void decrements_itself() {
        Position position = new Position(10);

        assertThat(position.sub(9)).isEqualTo(new Position(1));
    }
}