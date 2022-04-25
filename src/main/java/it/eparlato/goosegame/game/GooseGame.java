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

    public String movePlayer(String playerName, DiceRoll diceRoll) {
        if (!playersMap.containsKey(playerName)) {
            return String.format("Player %s does not exist. Add it to the Game first.", playerName);
        }

        Player player = playersMap.get(playerName);

        player.increasePositionBy(diceRoll.sumOfDiceValues());

        Position previousPosition = player.getPreviousPosition();
        Position currentPosition = player.getCurrentPosition();

        return String.format("%s rolls %d, %d. %s moves from %s to %s",
                player.name(), diceRoll.firstDiceValue(), diceRoll.secondDiceValue(),
                player.name(), previousPosition.tagValue(), currentPosition.tagValue());
    }

    private List<String> getPlayerNames() {
        return new ArrayList<>(playersMap.keySet());
    }

    public boolean isOver() {
        return false;
    }
}
