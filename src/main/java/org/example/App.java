package org.example;

import org.commands.*;
import org.lib.*;
import org.models.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    /**
     * Determines the file name that would be used for as database file name
     *
     * @return database file name
     */
    private static String getFileName() {
        String fileName = System.getenv("BASICDB_FILE_NAME");
        return fileName != null ? fileName : "db.json";
    }

    private static MyCollection loadCollectionFromFile(String fileName) throws IOException, DeserializationFailure {
        class Tickets implements Serializable {
            public ArrayList<Ticket> tickets;
        }

        BufferedDataReader bufferedDataReader = new BufferedDataReader(fileName);
        String data = bufferedDataReader.readData();
        JsonSerializer serializer = new JsonSerializer();
        Tickets tickets = serializer.deserialize(Tickets.class, data);

        MyCollection collection = new MyCollection();
        tickets.tickets.forEach(collection::insert);
        return collection;
    }

    private static MyCollection initializeCollection(String fileName) {
        try {
            return loadCollectionFromFile(fileName);
        } catch (Exception e) {
            System.out.println("Didn't load the collection from file '" + fileName + "' due to " + e.getClass().getSimpleName());
            System.out.println("Initializing a new one");
            return new MyCollection();
        }
    }

    private static String writeToTemporaryFile(String data) throws IOException {
        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);

        File dir = new File(tempDir);
        File file = File.createTempFile("lab5script", ".tmp", dir);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(data);
        bw.close();
        return tempDir + file.getName();
    }

    public static void main(String[] args) throws IOException {
        HistoryHolder historyHolder = new HistoryHolder(9);
        CommandIO commandIO = new CommandIO(new Scanner(System.in), System.out);
        String fileName = getFileName();

        MyCollection myCollection = initializeCollection(fileName);
        System.out.println("Collection of " + myCollection.stream().count() + " elements has been initialized");
        System.out.println();

        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new Help(commands));
        commands.add(new Info(myCollection));
        commands.add(new Add(myCollection));
        commands.add(new Show(myCollection));
        commands.add(new Update(myCollection));
        commands.add(new RemoveById(myCollection));
        commands.add(new Clear(myCollection));
        commands.add(new Save(myCollection, fileName));
        commands.add(new ExecuteScript(commands, historyHolder));
        commands.add(new Exit());
        commands.add(new AddIfMax(myCollection));
        commands.add(new AddIfMin(myCollection));
        commands.add(new History(historyHolder));
        commands.add(new CountByDiscount(myCollection));
        commands.add(new CountLessThenType(myCollection));
        commands.add(new PrintFieldDescendingType(myCollection));

        System.out.println("Hello There! Type 'help' to list available commands");
        try {
            while (true) {
                System.out.print("> ");
                Scanner scanner = new Scanner(System.in);
                String cmd = scanner.nextLine();

                String path = writeToTemporaryFile(cmd);
                Command executeScript = new ExecuteScript(commands, historyHolder);

                ArrayList<String> executeScriptArgs = new ArrayList<>();
                executeScriptArgs.add(executeScript.getName());
                executeScriptArgs.add(path);

                String res = executeScript.execute(executeScriptArgs, commandIO);
                System.out.println(res);
            }
        } catch (NoSuchElementException exception) {
            (new Exit()).execute(new ArrayList<>(), commandIO);
        }
    }
}
