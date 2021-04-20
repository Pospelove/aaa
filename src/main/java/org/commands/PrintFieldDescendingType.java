package org.commands;

import org.lib.MyCollection;
import org.lib.Reader;
import org.models.Ticket;

import java.util.Comparator;

public class PrintFieldDescendingType implements Command {
    private final MyCollection collection;

    public PrintFieldDescendingType(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) {
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
