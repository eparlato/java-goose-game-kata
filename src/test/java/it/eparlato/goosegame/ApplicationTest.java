package it.eparlato.goosegame;

import it.eparlato.goosegame.game.DiceRoll;
import it.eparlato.goosegame.game.GooseGame;
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

        when(gooseGame.addPlayer(playerName)).thenReturn(response);

        application.addPlayer(playerName);

        verify(gooseGame).addPlayer(playerName);
        verify(consoleOutput).show(response);
    }

    @Test
    void invokes_movePlayer_on_GooseGame_and_shows_its_result() {
        String playerName = "aPlayerName";
        int aValueForFirstDice = 5;
        int aValueForSecondDice = 5;

        DiceRoll diceRoll = new DiceRoll(aValueForFirstDice, aValueForSecondDice);
        String aResponse = "aPlayerName rolls 5, 5. aPlayerName moves from Start to 10";

        when(gooseGame.movePlayer(playerName, diceRoll))
                .thenReturn(aResponse);

        application.movePlayer(playerName, diceRoll);

        verify(gooseGame).movePlayer(playerName, diceRoll);
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
