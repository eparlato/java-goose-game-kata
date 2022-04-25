package it.eparlato.goosegame.game;

import java.util.LinkedHashMap;
import java.util.Map;

public class GooseGame {
    public static final int WINNING_POSITION = 63;
    private final Map<String, Player> playersMap = new LinkedHashMap<>();
    private boolean isOver = false;
    private final MessageResponseFactory responseFactory;

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
            return responseFactory.playerNotExistingMessage(playerName);
        }

        Player player = playersMap.get(playerName);

        player.increasePositionBy(diceRoll.sumOfDiceValues());

        if (isWinningPosition(player.getCurrentPosition())) {
            isOver = true;
            return responseFactory.buildWinningMessageFor(player, diceRoll);
        }

        return responseFactory.buildMoveMessageFrom(player, diceRoll);
    }

    private boolean isWinningPosition(Position position) {
        return position.value() == WINNING_POSITION;
    }

    public boolean isOver() {
        return isOver;
    }

}
