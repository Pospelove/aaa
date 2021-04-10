package org.commands;

import org.lib.CommandIO;
import org.lib.HistoryHolder;

public class History implements Command {
    private final HistoryHolder historyHolder;

    public History(HistoryHolder historyHolder) {
        this.historyHolder = historyHolder;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) {
        StringBuilder res = new StringBuilder();
        historyHolder.getHistory().forEach(x -> res.append(x).append("\n"));
        return res.toString().isEmpty() ? "\n" : res.toString();
    }

    @Override
    public String getDescription() {
        return "print the last 9 commands (without their arguments)";
    }
}
