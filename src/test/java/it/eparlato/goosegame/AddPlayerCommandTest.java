package it.eparlato.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}