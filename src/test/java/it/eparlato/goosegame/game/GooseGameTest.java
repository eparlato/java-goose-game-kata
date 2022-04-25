package it.eparlato.goosegame.game;

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

    @Test
    void moves_a_player_from_start_to_a_new_position() {
        game.addPlayer("Paperino");

        String response = game.movePlayer("Paperino", new DiceRoll(4, 4));

        assertThat(response).isEqualTo("Paperino rolls 4, 4. Paperino moves from Start to 8");
    }

    @Test
    void returns_an_error_message_when_we_try_to_move_a_player_that_does_not_exist() {
        String response = game.movePlayer("Paperino", new DiceRoll(4, 4));

        assertThat(response).isEqualTo("Player Paperino does not exist. Add it to the Game first.");
    }
}