package org.commands;

import org.lib.MyCollection;
import org.lib.Reader;
import org.models.Ticket;

public class Update implements Command {
    private final MyCollection collection;

    public Update(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) throws BadCommandArgumentException {
        Ticket element = new Ticket(commandArgument.getInteger(), reader);
        collection.update(element);
        return "updated\n";
    }

    @Override
    public String getDescription() {
        return "update an item in collection";
    }
}

