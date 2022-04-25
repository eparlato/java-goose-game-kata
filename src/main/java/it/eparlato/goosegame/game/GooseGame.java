package it.eparlato.goosegame.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GooseGame {
    private final Map<String, Player> playersMap = new LinkedHashMap<>();

    public String addPlayer(String playerName) {
        if (playersMap.containsKey(playerName)) {
           return String.format("%s: already existing player", playerName);
        }

        playersMap.put(playerName, new Player(playerName));

        return String.format("players: %s", String.join(", ", getPlayerNames()));
    }

    public String movePlayer(Player player, DiceRoll diceRoll) {
        return "";
    }

    private List<String> getPlayerNames() {
        return new ArrayList<>(playersMap.keySet());
    }

}
