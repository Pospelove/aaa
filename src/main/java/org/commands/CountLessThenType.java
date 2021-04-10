package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;
import org.models.TicketType;

public class CountLessThenType implements Command {
    private final MyCollection collection;

    public CountLessThenType(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) throws BadCommandArgumentException {
        TicketType type = commandArgument.getEnum(TicketType.class);
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
