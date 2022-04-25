package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.Application;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddPlayerCommandTest {
    final Application application = mock(Application.class);

    @Test
    void invokes_addPlayer_route_on_Application() {
        String aPlayerName = "A Player Name";

        ApplicationCommand command = new AddPlayerCommand(aPlayerName);

        command.executeOn(application);

        verify(application).addPlayer(aPlayerName);
    }
}