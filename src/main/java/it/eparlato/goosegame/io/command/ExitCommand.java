package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.Application;

public class ExitCommand implements ApplicationCommand {
    @Override
    public void executeOn(Application application) {
        application.quit();
    }
}
