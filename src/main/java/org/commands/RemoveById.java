package org.commands;

import org.lib.MyCollection;
import org.lib.Reader;

public class RemoveById implements Command {
    private final MyCollection collection;

    public RemoveById(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) throws BadCommandArgumentException {
        Long id = commandArgument.getLong();
        int n = collection.remove(id);
        return "removed " + n + " elements\n";
    }

    @Override
    public String getDescription() {
        return "remove an item from the collection by its id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}

