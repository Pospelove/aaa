package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.Ticket;

import java.util.Optional;
import java.util.stream.Stream;

// See 'AddIfMax'
// TODO: Fix DRY violation as soon as third similar overload comes

public class AddIfMin implements Command {
    private MyCollection collection;

    public AddIfMin(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) {
        Ticket element = new Ticket(collection.getFreeId(), commandIO);
        Ticket elementWithoutIds = element.cloneWithoutIdsAndDates();

        Stream<Ticket> lessOrEqualElements = collection.stream().filter(x -> x.cloneWithoutIdsAndDates().compareTo(elementWithoutIds) <= 0);

        Optional<Ticket> first = lessOrEqualElements.findFirst();
        if (first.isEmpty()) {
            collection.insert(element);
            return "added\n";
        } else {
            return "refused to add, a less or equal element present:\n" + first.get().toString() + "\n";
        }
    }

    @Override
    public String getDescription() {
        return "add a new item to the collection if its value is less than the value of the largest item in this collection";
    }

    @Override
    public String getName() {
        return "add_if_min";
    }
}
