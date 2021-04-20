package org.commands;

import org.lib.HistoryHolder;
import org.lib.Reader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExecuteScript implements Command {
    private final Set<Command> commands;
    private final HistoryHolder historyHolder;

    public ExecuteScript(Set<Command> commands, HistoryHolder historyHolder) {
        this.commands = commands;
        this.historyHolder = historyHolder;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) throws BadCommandArgumentException {
        if (recursionDepth > 10) {
            return "Maximum recursion depth exceeded\n";
        }
        try {
            ++recursionDepth;
            return runThisCommand(commandArgument, reader);
        } finally {
            --recursionDepth;
        }
    }

    private String runThisCommand(CommandArgument commandArgument, Reader reader) throws BadCommandArgumentException {
        String filename = commandArgument.getString();

        StringBuilder res = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(filename));

            try {
                while (true) {
                    String line = scanner.nextLine();

                    List<String> tokens = new ArrayList<>(Arrays.asList(line.split(" ")));
                    String commandName = tokens.get(0);
                    historyHolder.add(commandName);
                    CommandArgument subCommandArgument = new CommandArgument(tokens);
                    try {
                        Reader subCommandReader = recursionDepth >= 2 ? makeReader(scanner, reader) : reader;
                        String subCommandResult = findCommand(commandName).execute(subCommandArgument, subCommandReader);
                        res.append(subCommandResult);
                    } catch (NoSuchCommandException e) {
                        return "Unknown command '" + commandName + "'\n";
                    }
                }
            } catch (NoSuchElementException e) {
                return res.toString();
            }
        } catch (IOException e) {
            return e.toString() + "\n";
        }
    }

    private Command findCommand(String commandName) throws NoSuchCommandException {
        for (Command command : commands) {
            if (command.getName().equals(commandName)) {
                return command;
            }
        }
        throw new NoSuchCommandException();
    }

    private Reader makeReader(Scanner scanner, Reader base) {
        return new Reader() {
            @Override
            public String readString(String variableName) {
                base.print(makeTip(variableName));
                lastTip = makeTip(variableName);

                String line = scanner.nextLine();
                if (!line.startsWith(" ")) {
                    base.print("\n");
                    base.printErr(new Exception("Line must start with space"));
                    return "";
                }
                line = line.substring(1);
                if (!line.startsWith(lastTip)) {
                    base.print("\n");
                    base.printErr(new Exception("Line must start with " + lastTip));
                    return "";
                }

                String res = line.split(" ", 2)[1];
                base.print(res + "\n");
                return res;
            }

            @Override
            public void print(String str) {
                lastTip = str;
            }

            @Override
            public void printErr(Exception exception) {
                base.printErr(exception);
            }

            private String lastTip;
        };
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
