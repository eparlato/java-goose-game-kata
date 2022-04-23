package it.eparlato.goosegame;

public class NullCommand implements ApplicationCommand {
    @Override
    public void executeOn(Application application) {
        // does nothing, it's a null command
    }
}
