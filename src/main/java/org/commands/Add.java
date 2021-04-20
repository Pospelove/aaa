package org.commands;

import org.lib.MyCollection;
import org.lib.Reader;
import org.models.Ticket;

public class Add implements Command {
    private final MyCollection collection;

    public Add(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) {
        Ticket element = new Ticket(collection.getFreeId(), reader);
        collection.insert(element);
        return "added\n";
    }

    @Override
    public String getDescription() {
        return "add new item to collection";
    }
}
