package it.eparlato.goosegame.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GooseGame {
    public static final int WINNING_POSITION = 63;
    private final Map<String, Player> playersMap = new LinkedHashMap<>();
    private boolean isOver = false;

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

        String moveMessage = buildMoveMessageFrom(player, diceRoll);

        StringBuilder response = new StringBuilder(moveMessage);

        if (isWinningPosition(player.getCurrentPosition())) {
            isOver = true;
            response.append(String.format(". %s Wins!!", player.name()));
        }

        return response.toString();
    }

    private String buildMoveMessageFrom(Player player, DiceRoll diceRoll) {
        return String.format("%s rolls %d, %d. %s moves from %s to %s",
                player.name(), diceRoll.firstDiceValue(), diceRoll.secondDiceValue(),
                player.name(), player.getPreviousPosition().tagValue(), player.getCurrentPosition().tagValue());
    }

    private boolean isWinningPosition(Position position) {
        return position.value() == WINNING_POSITION;
    }

    private List<String> getPlayerNames() {
        return new ArrayList<>(playersMap.keySet());
    }

    public boolean isOver() {
        return isOver;
    }
}
