package it.eparlato.goosegame;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ExitCommandTest {
    @Test
    void tells_the_application_to_quit() {
        Application application = mock(Application.class);
        ApplicationCommand command = new ExitCommand();

        command.executeOn(application);

        verify(application).quit();
    }
}