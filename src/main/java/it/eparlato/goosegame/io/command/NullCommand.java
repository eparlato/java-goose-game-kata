package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.Application;

public class NullCommand implements ApplicationCommand {
    @Override
    public void executeOn(Application application) {
        // does nothing, it's a null command
    }
}
