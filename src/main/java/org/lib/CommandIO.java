package org.lib;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandIO {
    private final Scanner scanner;
    private final PrintStream printStream;

    public CommandIO(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }
}
