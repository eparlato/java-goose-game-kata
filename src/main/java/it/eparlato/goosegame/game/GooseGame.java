package it.eparlato.goosegame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GooseGame {
    private final List<Player> players = new ArrayList<>();

    public String addPlayer(Player player) {
        if (players.contains(player)) {
           return String.format("%s: already existing player", player.name());
        }

        players.add(player);

        return String.format("players: %s", String.join(", ", getPlayerNames()));
    }

    private List<String> getPlayerNames() {
        return players.stream().map(Player::name).collect(Collectors.toList());
    }
}
