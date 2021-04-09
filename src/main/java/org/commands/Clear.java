package org.commands;

import org.lib.CommandIO;
import org.lib.MyCollection;

import java.util.List;

public class Clear implements Command {
    private final MyCollection myCollection;

    public Clear(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        myCollection.clear();
        return "cleared\n";
    }

    @Override
    public String getDescription() {
        return "clear the collection";
    }
}
