package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;

public class Info implements Command {
    private final MyCollection myCollection;

    public Info(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) {
        String info = "Type: " + myCollection.getType();
        info += "\nInitialization date: " + myCollection.getInitializationDate();
        info += "\nElements count: " + myCollection.getSize();
        info += "\n";
        return info;
    }

    @Override
    public String getDescription() {
        return "print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream";
    }
}
