package org.lib;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandIO {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final PrintStream errorPrintStream;

    public CommandIO(Scanner scanner, PrintStream printStream, PrintStream errorPrintStream) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.errorPrintStream = errorPrintStream;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void print(String string) {
        printStream.print(string);
    }

    public void printErr(Exception exception) {
        errorPrintStream.println(exception.getMessage());
    }
}
