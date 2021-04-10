package org.commands;

import org.lib.Reader;

import java.util.List;

public class CommandArgument {
    public CommandArgument(List<String> tokens) {
        this.tokens = tokens;
    }

    public long getLong() throws BadCommandArgumentException {
        try {
            return Long.parseLong(tokens.get(1));
        } catch (IndexOutOfBoundsException exception) {
            throw new BadCommandArgumentException("a number", "nothing");
        } catch (NumberFormatException exception) {
            throw new BadCommandArgumentException("a number", tokens.get(1));
        }
    }

    public long getInteger() throws BadCommandArgumentException {
        try {
            return Integer.parseInt(tokens.get(1));
        } catch (IndexOutOfBoundsException exception) {
            throw new BadCommandArgumentException("a number", "nothing");
        } catch (NumberFormatException exception) {
            throw new BadCommandArgumentException("a number", tokens.get(1));
        }
    }

    public String getString() throws BadCommandArgumentException {
        try {
            return tokens.get(1);
        } catch (IndexOutOfBoundsException exception) {
            throw new BadCommandArgumentException("a string", "nothing");
        }
    }

    public <T extends Enum<T>> T getEnum(Class<T> enumeration) throws BadCommandArgumentException {
        try {
            return Enum.valueOf(enumeration, getString());
        } catch (IllegalArgumentException exception) {
            throw new BadCommandArgumentException("one of " + Reader.joinEnumConstants(enumeration), getString());
        }
    }

    private final List<String> tokens;
}
