package org.commands;

import org.lib.MyCollection;
import org.lib.Reader;

public class Clear implements Command {
    private final MyCollection myCollection;

    public Clear(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) {
        myCollection.clear();
        return "cleared\n";
    }

    @Override
    public String getDescription() {
        return "clear the collection";
    }
}
