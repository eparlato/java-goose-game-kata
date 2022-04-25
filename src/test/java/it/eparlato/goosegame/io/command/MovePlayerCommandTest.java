package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.Application;
import it.eparlato.goosegame.game.DiceRoll;
import it.eparlato.goosegame.game.Player;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MovePlayerCommandTest {
    private Application application = mock(Application.class);

    @Test
    void invokes_movePlayer_route_on_Application() {
        String playerName = "aPlayerName";
        int firstDiceValue = 6;
        int secondDiceValue = 4;
        ApplicationCommand command = new MovePlayerCommand(playerName, firstDiceValue, secondDiceValue);

        command.executeOn(application);

        verify(application).movePlayer(new Player(playerName),
                new DiceRoll(firstDiceValue, secondDiceValue));
    }
}