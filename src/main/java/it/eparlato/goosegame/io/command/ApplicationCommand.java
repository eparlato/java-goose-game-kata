package it.eparlato.goosegame.io.command;

import it.eparlato.goosegame.Application;

public interface ApplicationCommand {
    void executeOn(Application application);
}
