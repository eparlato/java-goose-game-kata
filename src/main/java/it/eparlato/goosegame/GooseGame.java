package it.eparlato.goosegame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GooseGame {
    private List<Player> players = new ArrayList<>();
    private String status;

    public void addPlayer(Player player) {
        players.add(player);

        List<String> playerNames = players.stream().map(Player::name).collect(Collectors.toList());

        status = String.format("players: %s", String.join(", ", playerNames));
    }

    public String status() {
        return status;
    }
}
