package it.eparlato.goosegame;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationE2ETest {
    @Test
    void quits_when_user_writes_exit() throws UnsupportedEncodingException {
        String userInput = "exit\n";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CommandParser commandParser = new CommandParser();

        ConsoleInput consoleInput = new ConsoleInput(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ConsoleOutput consoleOutput = new ConsoleOutput(new PrintStream(baos));
        Application application = new Application(consoleInput, consoleOutput, commandParser);

        application.run();

        assertThat(baos.toString("UTF-8")).isEqualTo("Bye.");
    }
}
