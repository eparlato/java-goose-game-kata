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
        game.addPlayer(new Player("Bar"));

        assertThat(game.status()).isEqualTo("players: Bar");
    }

    @Test
    void adds_two_players() {
        game.addPlayer(new Player("Anakin"));
        game.addPlayer(new Player("Obiwan"));

        assertThat(game.status()).isEqualTo("players: Anakin, Obiwan");
    }
}