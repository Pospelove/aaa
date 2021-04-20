package org.lib;

public class CommandIOReader extends Reader {
    public CommandIOReader(CommandIO io) {
        this.io = io;
    }

    @Override
    public String readString(String variableName) {
        String str = "";
        while (str.isEmpty()) {
            print(makeTip(variableName));
            str = io.nextLine();
        }
        return str;
    }


    @Override
    public void printErr(Exception exception) {
        io.printErr(exception);
    }

    @Override
    public void print(String str) {
        io.print(str);
    }

    private final CommandIO io;
}
