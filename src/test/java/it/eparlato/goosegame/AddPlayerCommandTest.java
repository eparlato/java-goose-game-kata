package it.eparlato.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddPlayerCommandTest {
    private final GooseGame gooseGame = mock(GooseGame.class);
    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application(null, null, null, gooseGame);
    }

    @Test
    void adds_a_player_to_a_goose_game() {
        ApplicationCommand command = new AddPlayerCommand("Foo");

        command.executeOn(application);

        verify(gooseGame).addPlayer(new Player("Foo"));
    }

    @Test
    void saves_GooseGame_command_response_on_Application() {
        ApplicationCommand command = new AddPlayerCommand("Player Name");

        // TODO: this is a smell, it would be better not to access to GooseGame from the command...
        Application application = mock(Application.class);
        when(application.getGooseGame()).thenReturn(gooseGame);
        when(gooseGame.addPlayer(new Player("Player Name"))).thenReturn("players: Player Name");

        command.executeOn(application);

        verify(application).setGooseGameResponse("players: Player Name");
    }
}