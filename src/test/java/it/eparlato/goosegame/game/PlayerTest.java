package it.eparlato.goosegame.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    void increases_its_current_position() {
        Player player = new Player("Jb");

        player.increasePositionBy(11);

        assertThat(player.getCurrentPosition()).isEqualTo(new Position(11));
        assertThat(player.getPreviousPosition()).isEqualTo(new Position(0));

        player.increasePositionBy(2);

        assertThat(player.getCurrentPosition()).isEqualTo(new Position(13));
        assertThat(player.getPreviousPosition()).isEqualTo(new Position(11));
    }

    @Test
    void knows_when_its_position_is_the_winning_one() {
        Player player = new Player("Bob");

        player.increasePositionBy(63);

        Position winningPosition = new Position(63);

        assertThat(player.getCurrentPosition()).isEqualTo(winningPosition);
        assertThat(player.isOnWinningPosition()).isTrue();
    }
}