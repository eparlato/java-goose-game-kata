package it.eparlato.goosegame;

import it.eparlato.goosegame.game.GooseGame;
import it.eparlato.goosegame.io.ConsoleInput;
import it.eparlato.goosegame.io.ConsoleOutput;
import it.eparlato.goosegame.io.command.CommandParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationE2ETest {

    private Application application;
    private ByteArrayOutputStream baos;

    @Test
    void quits_when_user_writes_exit() throws UnsupportedEncodingException {
        String commandSequence = "exit\n";

        setupApplicationWithCommandSequence(commandSequence);

        application.run();

        assertThatOutputContains("Bye.");
    }

    @Test
    void adds_players() throws UnsupportedEncodingException {
        String commandSequence =
                "add player Pippo\n" +
                "add player Pluto\n" +
                "exit\n";

        setupApplicationWithCommandSequence(commandSequence);

        application.run();

        assertThatOutputContains("players: Pippo");
        assertThatOutputContains("players: Pippo, Pluto");
    }

    @Test
    void duplicated_player() throws UnsupportedEncodingException {
        String commandSequence =
                "add player Pippo\n" +
                "add player Pippo\n" +
                "exit\n";

        setupApplicationWithCommandSequence(commandSequence);

        application.run();

        assertThatOutputContains("players: Pippo");
        assertThatOutputContains("Pippo: already existing player");
    }


    private void setupApplicationWithCommandSequence(String commandSequence) {
        baos = new ByteArrayOutputStream();
        CommandParser commandParser = new CommandParser();

        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream(commandSequence.getBytes(StandardCharsets.UTF_8)));
        ConsoleOutput consoleOutput = new ConsoleOutput(new PrintStream(baos));
        application = new Application(consoleInput, consoleOutput, commandParser, new GooseGame());
    }

    private void assertThatOutputContains(String output) throws UnsupportedEncodingException {
        assertThat(baos.toString("UTF-8")).contains(output);
    }
}
