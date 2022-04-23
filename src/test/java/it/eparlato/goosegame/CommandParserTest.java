package it.eparlato.goosegame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandParserTest {
    private CommandParser parser = new CommandParser();
    private ApplicationCommand command;

    @Test
    void builds_ExitCommand() {
        command = parser.getCommandFor("exit");

        assertThat(command).isInstanceOf(ExitCommand.class);
    }

    @Test
    void builds_AddPlayerCommand() {
        command = parser.getCommandFor("add player Pippo");

        assertThat(command).isEqualTo(new AddPlayerCommand("Pippo"));
    }

    @Test
    void builds_NullCommand_when_input_is_not_recognized() {
        command = parser.getCommandFor("");

        assertThat(command).isInstanceOf(NullCommand.class);
    }
}