package org.commands;

import org.lib.CommandIO;
import org.lib.HistoryHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecuteScript implements Command {
    private final ArrayList<Command> commands;
    private final HistoryHolder historyHolder;

    public ExecuteScript(ArrayList<Command> commands, HistoryHolder historyHolder) {
        this.commands = commands;
        this.historyHolder = historyHolder;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) throws BadCommandArgumentException {
        if (recursionDepth > 10) {
            return "Maximum recursion depth exceeded\n";
        }
        try {
            ++recursionDepth;
            return runThisCommand(commandArgument, commandIO);
        } finally {
            --recursionDepth;
        }
    }

    private String runThisCommand(CommandArgument commandArgument, CommandIO commandIO) throws BadCommandArgumentException {
        String filename = commandArgument.getString();

        StringBuilder res = new StringBuilder();
        try {
            Stream<String> lines = Files.lines(Path.of(filename));
            for (String line : lines.collect(Collectors.toSet())) {
                List<String> tokens = new ArrayList<>(Arrays.asList(line.split(" ")));
                String commandName = tokens.get(0);
                historyHolder.add(commandName);
                CommandArgument subCommandArgument = new CommandArgument(tokens);
                try {
                    String subCommandResult = findCommand(commandName).execute(subCommandArgument, commandIO);
                    res.append(subCommandResult);
                } catch (NoSuchCommandException e) {
                    return "Unknown command '" + commandName + "'\n";
                }
            }
        } catch (IOException e) {
            return e.toString() + "\n";
        }
        return res.toString();
    }

    private String runSubCommand(Command subCommand, CommandArgument subCommandArgument, CommandIO commandIO) throws BadCommandArgumentException {
        return subCommand.execute(subCommandArgument, commandIO);
    }

    private Command findCommand(String commandName) throws NoSuchCommandException {
        for (Command command : commands) {
            if (command.getName().equals(commandName)) {
                return command;
            }
        }
        throw new NoSuchCommandException();
    }

    private static class NoSuchCommandException extends Exception {
    }

    @Override
    public String getDescription() {
        return "read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    private int recursionDepth = 0;
}
