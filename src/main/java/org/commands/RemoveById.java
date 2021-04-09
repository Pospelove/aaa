package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;

import java.util.List;

public class RemoveById implements Command {
    private final MyCollection collection;

    public RemoveById(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        Long id = Long.parseLong(stringArguments.get(1));
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

