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

    public String playerNotExistingMessage(String playerName) {
        return String.format("Player %s does not exist. Add it to the Game first.", playerName);
    }

    private List<String> getPlayerNames(Map<String, Player> players) {
        return new ArrayList<>(players.keySet());
    }

    public String buildMoveMessageFrom(Player player, DiceRoll diceRoll) {
        return String.format("%s rolls %d, %d. %s moves from %s to %s",
                player.name(), diceRoll.firstDiceValue(), diceRoll.secondDiceValue(),
                player.name(), textValueOf(player.getPreviousPosition()), textValueOf(player.getCurrentPosition()));
    }

    private String textValueOf(Position position) {
        if (position.value() == 0)
            return "Start";

        return String.valueOf(position.value());
    }

    public String buildWinningMessageFor(Player player, DiceRoll diceRoll) {
        return String.format("%s. %s Wins!!", buildMoveMessageFrom(player, diceRoll), player.name());
    }

    public String buildPlayerBouncesMessageFor(Player player, DiceRoll roll) {
        return String.format("%s rolls %d, %d. %s moves from %d to %d. %s bounces! %s returns to %d",
                player.name(), roll.firstDiceValue(), roll.secondDiceValue(),
                player.name(), player.getPreviousPosition().value(), Player.WINNING_POSITION_VALUE,
                player.name(),
                player.name(), player.getCurrentPosition().value());
    }
}
