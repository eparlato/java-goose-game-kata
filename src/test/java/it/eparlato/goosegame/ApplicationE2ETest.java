package it.eparlato.goosegame;

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

        assertThatOutputIsEqualTo("Bye.");
    }

    @Test
    @Disabled
    void adds_a_player() throws UnsupportedEncodingException {
        String commandSequence = "add player Pippo\n";

        setupApplicationWithCommandSequence(commandSequence);

        application.run();

        assertThatOutputIsEqualTo("players: Pippo");
    }

    private void setupApplicationWithCommandSequence(String commandSequence) {
        baos = new ByteArrayOutputStream();
        CommandParser commandParser = new CommandParser();

        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream(commandSequence.getBytes(StandardCharsets.UTF_8)));
        ConsoleOutput consoleOutput = new ConsoleOutput(new PrintStream(baos));
        application = new Application(consoleInput, consoleOutput, commandParser);
    }

    private void assertThatOutputIsEqualTo(String expectedOutput) throws UnsupportedEncodingException {
        assertThat(baos.toString("UTF-8")).isEqualTo(expectedOutput);
    }
}
