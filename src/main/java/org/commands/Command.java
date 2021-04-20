package org.commands;

import org.lib.Reader;

public interface Command {
    String execute(CommandArgument commandArgument, Reader reader) throws BadCommandArgumentException;

    String getDescription();

    default String getName() {
        return getClass().getSimpleName().toLowerCase();
    }
}
