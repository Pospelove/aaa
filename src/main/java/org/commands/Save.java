package org.commands;

import org.lib.BufferedDataWriter;
import org.lib.CommandIO;
import org.lib.JsonSerializer;
import org.lib.MyCollection;
import org.models.Ticket;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Save implements Command {
    private final MyCollection collection;
    private final String fileName;

    public Save(MyCollection collection, String fileName) {
        this.collection = collection;
        this.fileName = fileName;
    }

    @Override
    public String execute(List<String> stringArguments, CommandIO commandIO) {
        class Tickets implements Serializable {
            public ArrayList<Ticket> tickets;
        }

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
