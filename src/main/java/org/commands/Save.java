package org.commands;

import org.lib.BufferedDataWriter;
import org.lib.JsonSerializer;
import org.lib.MyCollection;
import org.lib.Reader;
import org.models.Tickets;

import java.io.IOException;
import java.util.ArrayList;

public class Save implements Command {
    private final MyCollection collection;
    private final String fileName;

    public Save(MyCollection collection, String fileName) {
        this.collection = collection;
        this.fileName = fileName;
    }

    @Override
    public String execute(CommandArgument commandArgument, Reader reader) {
        Tickets tickets = new Tickets();
        tickets.tickets = new ArrayList<>();
        collection.forEach(x -> tickets.tickets.add(x));

        JsonSerializer jsonSerializer = new JsonSerializer();

        BufferedDataWriter bufferedDataWriter = new BufferedDataWriter(fileName);
        try {
            bufferedDataWriter.writeData(jsonSerializer.serialize(tickets));
            return "saved\n";
        } catch (IOException e) {
            return "failed to save due to " + e.toString() + "\n";
        }
    }

    @Override
    public String getDescription() {
        return "save collection to file";
    }
}
