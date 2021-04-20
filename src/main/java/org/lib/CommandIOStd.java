package org.lib;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandIOStd implements CommandIO {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final PrintStream errorPrintStream;

    public CommandIOStd(Scanner scanner, PrintStream printStream, PrintStream errorPrintStream) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.errorPrintStream = errorPrintStream;
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void print(String string) {
        printStream.print(string);
    }

    @Override
    public void printErr(Exception exception) {
        errorPrintStream.println(exception.getClass().getSimpleName() + " " + exception.getMessage());
    }
}
