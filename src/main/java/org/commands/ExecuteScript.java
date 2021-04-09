package org.commands;

import org.lib.CommandIO;
import org.lib.HistoryHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExecuteScript implements Command {
    private ArrayList<Command> commands;
    private HistoryHolder historyHolder;

    public ExecuteScript(ArrayList<Command> commands, HistoryHolder historyHolder) {
        this.commands = commands;
        this.historyHolder = historyHolder;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        if (recursionDepth > 10) {
            return "Maximum recursion depth exceeded\n";
        }

        ++recursionDepth;
        String res = "";
        try {
            res = executeUnsafe(stringArguments, commandIO);
        } finally {
            --recursionDepth;
        }

        return res;
    }

    private String executeUnsafe(List<String> stringArguments, CommandIO commandIO) {
        String filename = stringArguments.get(1);
        commandIO.getPrintStream().println("Executing " + filename + "\n");

        StringBuilder res = new StringBuilder();
        try {
            Files.lines(Path.of(filename)).forEach(line -> {
                List<String> args = new ArrayList<>(Arrays.asList(line.split(" ")));

                // Fill with a default value "0"
                while (args.size() < 10) {
                    args.add("0");
                }

                res.append(runCommand(args, commandIO));
            });
        } catch (IOException e) {
            return e.toString() + "\n";
        }
        return res.toString();
    }

    private String runCommand(List<String> stringArguments, CommandIO commandIO) {
        historyHolder.add(stringArguments.get(0));

        Scanner elementArgument = new Scanner(System.in);

        for (Command command : commands) {
            if (command.getName().equals(stringArguments.get(0))) {
                return command.execute(stringArguments, commandIO);
            }
        }
        return "Unknown command '" + stringArguments.get(0) + "'\n";
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
