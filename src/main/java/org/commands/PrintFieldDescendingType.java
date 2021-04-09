package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.Ticket;

import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingType implements Command {
    private final MyCollection collection;

    public PrintFieldDescendingType(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        return collection
                .stream()
                .map(Ticket::getType)
                .sorted(Comparator.reverseOrder())
                .map(Enum::toString)
                .reduce((x, y) -> x + "\n" + y)
                .orElse("") + "\n";
    }

    @Override
    public String getDescription() {
        return "print the values of the type field of all elements in descending order";
    }

    @Override
    public String getName() {
        return "print_field_descending_type";
    }
}
