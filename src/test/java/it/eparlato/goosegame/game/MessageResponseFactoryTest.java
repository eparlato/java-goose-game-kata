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
}