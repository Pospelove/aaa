package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;

public class CountByDiscount implements Command {
    private final MyCollection collection;

    public CountByDiscount(MyCollection collection) {
        this.collection = collection;
    }

    @Override
    public String execute(CommandArgument commandArgument, CommandIO commandIO) throws BadCommandArgumentException {
        long discount = commandArgument.getLong();
        long count = collection.stream().filter(x -> x.getDiscount() == discount).count();
        return "total: " + count + "\n";
    }

    @Override
    public String getDescription() {
        return "display the number of elements, the value of the discount field of which is equal to the given one";
    }

    @Override
    public String getName() {
        return "count_by_discount";
    }
}
