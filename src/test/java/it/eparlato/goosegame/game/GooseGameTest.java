package it.eparlato.goosegame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GooseGameTest {
    private final MessageResponseFactory messageFactory = mock(MessageResponseFactory.class);
    private GooseGame game;

    @BeforeEach
    void setUp() {
        game = new GooseGame(messageFactory);
    }

    @Test
    void adds_a_player() {
        String listOfPlayersMessage = "players: Bar";
        when(messageFactory.listOfPlayersMessage(anyMap())).thenReturn(listOfPlayersMessage);

        String response = game.addPlayer("Bar");

        assertThat(response).isEqualTo(listOfPlayersMessage);
    }

    @Test
    void adds_three_players() {
        String listOfPlayersMessage = "players: Anakin, Yoda, Obiwan";
        when(messageFactory.listOfPlayersMessage(anyMap())).thenReturn(listOfPlayersMessage);

        game.addPlayer("Anakin");
        game.addPlayer("Yoda");
        String response = game.addPlayer("Obiwan");

        assertThat(response).isEqualTo(listOfPlayersMessage);
    }

    @Test
    void does_not_add_a_player_that_already_exists() {
        String playerName = "Pluto";
        String alreadyExistingPlayerMessage = "Pluto: already existing player";

        when(messageFactory.alreadyExistingPlayerMessage(playerName)).thenReturn(alreadyExistingPlayerMessage);
        game.addPlayer(playerName);
        String response = game.addPlayer(playerName);

        assertThat(response).isEqualTo(alreadyExistingPlayerMessage);
    }

    @Test
    void moves_a_player_from_start_to_a_new_position() {
        String playerName = "Paperino";
        DiceRoll diceRoll = new DiceRoll(4, 4);
        String moveMessage = "Paperino rolls 4, 4. Paperino moves from Start to 8";
        when(messageFactory.buildMoveMessageFrom(new Player(playerName), diceRoll)).thenReturn(moveMessage);

        game.addPlayer(playerName);

        String response = game.movePlayer(playerName, diceRoll);

        assertThat(response).isEqualTo(moveMessage);
    }

    @Test
    void returns_an_error_message_when_we_try_to_move_a_player_that_does_not_exist() {
        String playerNotExistingMessage = "Player Paperino does not exist. Add it to the Game first.";
        String playerName = "Paperino";

        when(messageFactory.playerNotExistingMessage(playerName)).thenReturn(playerNotExistingMessage);
        String response = game.movePlayer(playerName, new DiceRoll(4, 4));

        assertThat(response).isEqualTo(playerNotExistingMessage);
    }

    @Test
    void quits_itself_and_returns_a_message_if_a_player_wins_after_moving() {
        String playerName = "Pippo";
        DiceRoll winningDiceRoll = new DiceRoll(1, 2);
        String winningMessage = "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!";
        when(messageFactory.buildWinningMessageFor(new Player(playerName), winningDiceRoll)).thenReturn(winningMessage);

        game.addPlayer(playerName);

        game.movePlayer(playerName, new DiceRoll(6, 6));
        game.movePlayer(playerName, new DiceRoll(6, 6));
        game.movePlayer(playerName, new DiceRoll(6, 6));
        game.movePlayer(playerName, new DiceRoll(6, 6));
        game.movePlayer(playerName, new DiceRoll(6, 6));
        String response = game.movePlayer(playerName, winningDiceRoll);

        assertThat(game.isOver()).isTrue();
        assertThat(response).isEqualTo("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!");
    }
}