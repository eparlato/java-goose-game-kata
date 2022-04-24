package it.eparlato.goosegame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GooseGame {
    private final List<Player> players = new ArrayList<>();

    public String addPlayer(Player player) {
        players.add(player);

        List<String> playerNames = players.stream().map(Player::name).collect(Collectors.toList());

        return String.format("players: %s", String.join(", ", playerNames));
    }
}
