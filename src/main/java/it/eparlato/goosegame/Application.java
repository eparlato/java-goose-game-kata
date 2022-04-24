package it.eparlato.goosegame;

public class Application {
    public static final String EXIT_MESSAGE = "Bye.";
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private final CommandParser commandParser;
    private final GooseGame gooseGame;
    private boolean running = true;

    public Application(ConsoleInput consoleInput, ConsoleOutput consoleOutput, CommandParser commandParser,
                       GooseGame gooseGame) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
        this.commandParser = commandParser;
        this.gooseGame = gooseGame;
    }

    public void run() {
        while(running) {
            String userInput = consoleInput.getUserInput();
            ApplicationCommand command = commandParser.buildCommandFromInput(userInput);

            command.executeOn(this);
        }
    }

    public void quit() {
        running = false;
        consoleOutput.show(EXIT_MESSAGE);
    }

    public GooseGame getGooseGame() {
        return gooseGame;
    }

    public void addPlayerWithName(String playerName) {
        String response = gooseGame.addPlayer(new Player(playerName));
        consoleOutput.show(response);
    }

    public static void main(String[] args) {
        Application application = new Application(new ConsoleInput(System.in), new ConsoleOutput(System.out),
                new CommandParser(), new GooseGame());

        application.run();
    }
}
