package org.commands;

import org.lib.CommandIO;

import java.util.List;

public interface Command {
    String execute(List<String> stringArguments, CommandIO commandIO);

    String getDescription();

    default String getName() {
        return getClass().getSimpleName().toLowerCase();
    }
}
