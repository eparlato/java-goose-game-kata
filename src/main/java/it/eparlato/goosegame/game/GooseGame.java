package it.eparlato.goosegame.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GooseGame {
    public static final int WINNING_POSITION = 63;
    private final Map<String, Player> playersMap = new LinkedHashMap<>();
    private boolean isOver = false;
    private MessageResponseFactory responseFactory;

    public GooseGame(MessageResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public String addPlayer(String playerName) {
        if (playersMap.containsKey(playerName)) {
            return responseFactory.alreadyExistingPlayerMessage(playerName);
        }

        playersMap.put(playerName, new Player(playerName));

        return responseFactory.listOfPlayersMessage(playersMap);
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
                player.name(), textValueOf(player.getPreviousPosition()), textValueOf(player.getCurrentPosition()));
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

    private String textValueOf(Position position) {
        if (position.value() == 0)
            return "Start";

        return String.valueOf(position.value());
    }
}
