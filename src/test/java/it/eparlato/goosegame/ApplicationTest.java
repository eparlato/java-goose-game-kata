package it.eparlato.goosegame;

import it.eparlato.goosegame.game.GooseGame;
import it.eparlato.goosegame.game.Player;
import it.eparlato.goosegame.io.ConsoleInput;
import it.eparlato.goosegame.io.ConsoleOutput;
import it.eparlato.goosegame.io.command.CommandParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ApplicationTest {
    private final ConsoleInput consoleInput = mock(ConsoleInput.class);
    private final ConsoleOutput consoleOutput = mock(ConsoleOutput.class);
    private final CommandParser commandParser = mock(CommandParser.class);
    private final GooseGame gooseGame = mock(GooseGame.class);
    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application(consoleInput, consoleOutput, commandParser, gooseGame);
    }

    @Test
    void invokes_addPlayer_on_GooseGame_and_shows_its_result() {
        String playerName = "A_Player_Name";
        String response = "players: A_Player_Name";
        Player aNewPlayer = new Player(playerName);

        when(gooseGame.addPlayer(aNewPlayer)).thenReturn(response);

        application.addPlayerWithName(playerName);

        verify(gooseGame).addPlayer(aNewPlayer);
        verify(consoleOutput).show(response);
    }

    @Test
    void invokes_movePlayer_on_GooseGame_and_shows_its_result() {
        String playerName = "aPlayerName";
        int aValueForFirstDice = 5;
        int aValueForSecondDice = 5;

        String aResponse = "aPlayerName rolls 5, 5. aPlayerName moves from Start to 10";
        when(gooseGame.movePlayer(new Player(playerName), aValueForFirstDice, aValueForSecondDice))
                .thenReturn(aResponse);

        application.movePlayer(playerName, aValueForFirstDice, aValueForSecondDice);

        verify(gooseGame).movePlayer(new Player("aPlayerName"), aValueForFirstDice, aValueForSecondDice);
        verify(consoleOutput).show(aResponse);
    }

    @Test
    void quits_itself_and_show_a_goodbye_message() {
        application.quit();

        application.run();

        verify(consoleInput, never()).getUserInput();
        verify(commandParser, never()).buildCommandFromInput(anyString());
        verify(consoleOutput).show(Application.EXIT_MESSAGE);
    }
}
