package it.eparlato.goosegame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandParserTest {
    private final CommandParser parser = new CommandParser();

    @Test
    void builds_ExitCommand() {
        assertThat(parser.buildCommandFromInput("exit")).isInstanceOf(ExitCommand.class);
    }

    @Test
    void builds_AddPlayerCommand() {
        assertThat(parser.buildCommandFromInput("add player Pippo")).isEqualTo(new AddPlayerCommand("Pippo"));
    }

    @Test
    void builds_NullCommand_when_input_is_not_recognized() {
        assertThat(parser.buildCommandFromInput("")).isInstanceOf(NullCommand.class);
    }
}