package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.Ticket;

public class Update implements Command {
    private final MyCollection collection;

    public Update(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) throws BadCommandArgumentException {
        Ticket element = new Ticket(commandArgument.getInteger(), commandIO);
        collection.update(element);
        return "updated\n";
    }

    @Override
    public String getDescription() {
        return "update an item in collection";
    }
}

