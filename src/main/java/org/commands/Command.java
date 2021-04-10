package org.commands;

import org.lib.CommandIO;

public interface Command {
    String execute(CommandArgument commandArgument, CommandIO commandIO) throws BadCommandArgumentException;

    String getDescription();

    default String getName() {
        return getClass().getSimpleName().toLowerCase();
    }
}
