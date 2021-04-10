package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.Ticket;

public class Add implements Command {
    private final MyCollection collection;

    public Add(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) {
        Ticket element = new Ticket(collection.getFreeId(), commandIO);
        collection.insert(element);
        return "added\n";
    }

    @Override
    public String getDescription() {
        return "add new item to collection";
    }
}
