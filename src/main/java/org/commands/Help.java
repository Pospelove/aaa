package org.commands;

import org.lib.Reader;

import java.util.Set;

public class Help implements Command {
    private final Set<Command> commandList;

    public Help(Set<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) {
        final StringBuilder res = new StringBuilder();
        for (Command command : this.commandList) {
            res.append(command.getName());
            res.append(" - ");
            res.append(command.getDescription());
            res.append("\n");
        }
        return res.toString();
    }

    @Override
    public String getDescription() {
        return "list and describe available commands";
    }
}
