package it.eparlato.goosegame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GooseGameTest {
    private GooseGame game;

    @BeforeEach
    void setUp() {
        game = new GooseGame(new MessageResponseFactory());
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

    @Test
    void quits_itself_and_returns_a_message_if_a_player_wins_after_moving() {
        game.addPlayer("Pippo");

        game.movePlayer("Pippo", new DiceRoll(6, 6));
        game.movePlayer("Pippo", new DiceRoll(6, 6));
        game.movePlayer("Pippo", new DiceRoll(6, 6));
        game.movePlayer("Pippo", new DiceRoll(6, 6));
        game.movePlayer("Pippo", new DiceRoll(6, 6));
        String response = game.movePlayer("Pippo", new DiceRoll(1, 2));

        assertThat(game.isOver()).isTrue();
        assertThat(response).isEqualTo("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!");
    }
}