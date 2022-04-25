package it.eparlato.goosegame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        String response = game.addPlayer("Bar");

        assertThat(response).isEqualTo("players: Bar");
    }

    @Test
    void adds_three_players() {
        game.addPlayer("Anakin");
        game.addPlayer("Yoda");
        String response = game.addPlayer("Obiwan");

        assertThat(response).isEqualTo("players: Anakin, Yoda, Obiwan");
    }

    @Test
    void does_not_add_a_player_that_already_exists() {
        game.addPlayer("Pluto");
        String response = game.addPlayer("Pluto");

        assertThat(response).isEqualTo("Pluto: already existing player");
    }
}