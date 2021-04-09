package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.Ticket;

import java.util.List;

public class Update implements Command {
    private final MyCollection collection;

    public Update(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        Ticket element = new Ticket(Integer.parseInt(stringArguments.get(1)), commandIO);
        collection.update(element);
        return "updated\n";
    }

    @Override
    public String getDescription() {
        return "update an item in collection";
    }
}

