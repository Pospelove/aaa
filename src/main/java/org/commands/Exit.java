package org.commands;

import org.lib.CommandIO;

public class Exit implements Command {

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) {
        System.out.print("Have a nice day");
        System.exit(0);
        return "\n";
    }

    @Override
    public String getDescription() {
        return "exit the program (without saving to file)";
    }
}
