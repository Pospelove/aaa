package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.Ticket;

import java.util.List;

public class Add implements Command {
    private final MyCollection collection;

    public Add(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        Ticket element = new Ticket(collection.getFreeId(), commandIO);
        collection.insert(element);
        return "added\n";
    }

    @Override
    public String getDescription() {
        return "add new item to collection";
    }
}
