package it.eparlato.goosegame;

public class ExitCommand implements ApplicationCommand {
    @Override
    public void executeOn(Application application) {
        application.quit();
    }
}
