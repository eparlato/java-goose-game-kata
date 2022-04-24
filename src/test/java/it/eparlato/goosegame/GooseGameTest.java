package it.eparlato.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GooseGameTest {
    private GooseGame game;

    @BeforeEach
    void setUp() {
        game = new GooseGame();
    }

    @Test
    void adds_a_player() {
        String status = game.addPlayer(new Player("Bar"));

        assertThat(status).isEqualTo("players: Bar");
    }

    @Test
    void adds_three_players() {
        game.addPlayer(new Player("Anakin"));
        game.addPlayer(new Player("Yoda"));
        String status = game.addPlayer(new Player("Obiwan"));

        assertThat(status).isEqualTo("players: Anakin, Yoda, Obiwan");
    }
}