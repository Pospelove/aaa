package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;

public class Show implements Command {
    private final MyCollection collection;

    public Show(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) {
        StringBuilder res = new StringBuilder();
        collection.forEach(element -> res.append(element.toString()).append("\n"));
        return res.toString();
    }

    @Override
    public String getDescription() {
        return "enumerate collection elements";
    }
}
