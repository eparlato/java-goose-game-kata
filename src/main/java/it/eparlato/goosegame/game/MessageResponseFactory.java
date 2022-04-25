package it.eparlato.goosegame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageResponseFactory {
    public String alreadyExistingPlayerMessage(String playerName) {
        return String.format("%s: already existing player", playerName);
    }

    public String listOfPlayersMessage(Map<String, Player> players) {
        return String.format("players: %s", String.join(", ", getPlayerNames(players)));
    }

    private List<String> getPlayerNames(Map<String, Player> players) {
        return new ArrayList<>(players.keySet());
    }
}
