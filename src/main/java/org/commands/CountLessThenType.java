package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.lib.Reader;
import org.models.TicketType;

import java.util.List;

public class CountLessThenType implements Command {
    private final MyCollection collection;

    public CountLessThenType(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        TicketType type;
        try {
            type = TicketType.valueOf(stringArguments.get(1));
        } catch (IllegalArgumentException exception) {
            return "Expected one of " + Reader.joinEnumConstants(TicketType.class) + ", but got " + stringArguments.get(1);
        }
        long n = collection.stream().filter(x -> x.getType().ordinal() < type.ordinal()).count();
        return "total: " + n + "\n";
    }

    @Override
    public String getDescription() {
        return "print the number of elements, the value of the type field of which is less than the specified one";
    }

    @Override
    public String getName() {
        return "count_less_then_type";
    }
}
