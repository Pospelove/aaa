package org.commands;

import org.lib.MyCollection;
import org.lib.Reader;
import org.models.Ticket;

import java.util.Optional;
import java.util.stream.Stream;

// See 'AddIfMin'
// TODO: Fix DRY violation as soon as third similar overload comes

public class AddIfMax implements Command {
    private MyCollection collection;

    public AddIfMax(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) {
        Ticket element = new Ticket(collection.getFreeId(), reader);
        Ticket elementWithoutIds = element.cloneWithoutIdsAndDates();

        Stream<Ticket> greaterOrEqualElements = collection.stream().filter(x -> x.cloneWithoutIdsAndDates().compareTo(elementWithoutIds) >= 0);

        Optional<Ticket> first = greaterOrEqualElements.findFirst();
        if (first.isEmpty()) {
            collection.insert(element);
            return "added\n";
        } else {
            return "refused to add, a greater or equal element present:\n" + first.get().toString() + "\n";
        }
    }

    @Override
    public String getDescription() {
        return "add a new item to the collection if its value is greater than the value of the largest item in this collection";
    }

    @Override
    public String getName() {
        return "add_if_max";
    }
}
