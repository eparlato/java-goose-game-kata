package it.eparlato.goosegame.game;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MessageResponseFactoryTest {
    private final MessageResponseFactory responseFactory = new MessageResponseFactory();

    @Test
    void builds_already_existing_player_message() {
        String playerName = "Pippo";

        String expected = "Pippo: already existing player";
        assertThat(responseFactory.alreadyExistingPlayerMessage(playerName)).isEqualTo(expected);
    }

    @Test
    void builds_players_list_message() {
        Map<String, Player> players = new LinkedHashMap<String, Player>() {{
            put("Pippo", new Player("Pippo"));
            put("Pluto", new Player("Pluto"));
            put("Paperino", new Player("Paperino"));
        }};

        String expected = "players: Pippo, Pluto, Paperino";
        assertThat(responseFactory.listOfPlayersMessage(players)).isEqualTo(expected);
    }

    @Test
    void builds_player_not_existing_message() {
        String playerName = "Pluto";

        String expected = "Player Pluto does not exist. Add it to the Game first.";
        assertThat(responseFactory.playerNotExistingMessage(playerName)).isEqualTo(expected);
    }

    @Test
    void builds_player_move_message() {
        Player player = new Player("Paperino");
        DiceRoll diceRoll = new DiceRoll(4, 5);

        player.increasePositionBy(diceRoll.sumOfDiceValues());

        String expected = "Paperino rolls 4, 5. Paperino moves from Start to 9";
        assertThat(responseFactory.buildMoveMessageFrom(player, diceRoll)).isEqualTo(expected);
    }

    @Test
    void builds_player_wins_message() {
        Player player = new Player("Paperino");
        DiceRoll diceRoll = new DiceRoll(4, 5);

        player.increasePositionBy(diceRoll.sumOfDiceValues());

        String expected = "Paperino rolls 4, 5. Paperino moves from Start to 9. Paperino Wins!!";

        assertThat(responseFactory.buildWinningMessageFor(player, diceRoll)).isEqualTo(expected);
    }

    @Test
    void builds_player_bounces_message() {
        Player player = new Player("John");
        DiceRoll roll = new DiceRoll(1, 2);
        player.increasePositionBy(62);
        player.increasePositionBy(3);

        String expected = "John rolls 1, 2. John moves from 62 to 63. John bounces! John returns to 61";
        assertThat(responseFactory.buildPlayerBouncesMessageFor(player, roll)).isEqualTo(expected);
    }
}