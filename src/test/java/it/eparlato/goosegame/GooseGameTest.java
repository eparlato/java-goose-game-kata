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
        String response = game.addPlayer(new Player("Bar"));

        assertThat(response).isEqualTo("players: Bar");
    }

    @Test
    void adds_three_players() {
        game.addPlayer(new Player("Anakin"));
        game.addPlayer(new Player("Yoda"));
        String response = game.addPlayer(new Player("Obiwan"));

        assertThat(response).isEqualTo("players: Anakin, Yoda, Obiwan");
    }

    @Test
    void does_not_add_a_player_that_already_exists() {
        game.addPlayer(new Player("Pluto"));
        String response = game.addPlayer(new Player("Pluto"));

        assertThat(response).isEqualTo("Pluto: already existing player");
    }
}