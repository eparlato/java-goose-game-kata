package it.eparlato.goosegame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Jb");
    }

    @Test
    void increases_its_current_position() {
        player.increasePositionBy(11);

        assertThat(player.getCurrentPosition()).isEqualTo(new Position(11));
        assertThat(player.getPreviousPosition()).isEqualTo(new Position(0));

        player.increasePositionBy(2);

        assertThat(player.getCurrentPosition()).isEqualTo(new Position(13));
        assertThat(player.getPreviousPosition()).isEqualTo(new Position(11));
    }

    @Test
    void knows_when_its_position_is_the_winning_one() {
        player.increasePositionBy(63);

        Position winningPosition = new Position(63);

        assertThat(player.getCurrentPosition()).isEqualTo(winningPosition);
        assertThat(player.isOnWinningPosition()).isTrue();
    }

    @Test
    void bounces_back_if_increase_its_position_beyond_winning_position() {
        player.increasePositionBy(60);
        player.increasePositionBy(5);

        assertThat(player.hasBounced()).isTrue();
        assertThat(player.getPreviousPosition()).isEqualTo(new Position(60));
        assertThat(player.getCurrentPosition()).isEqualTo(new Position(61));
    }

    @Test
    void resets_bounces_when_increases_position() {
        player.increasePositionBy(60);
        player.increasePositionBy(7);
        player.increasePositionBy(2);

        assertThat(player.hasBounced()).isFalse();
        assertThat(player.getPreviousPosition()).isEqualTo(new Position(59));
        assertThat(player.getCurrentPosition()).isEqualTo(new Position(61));
    }
}