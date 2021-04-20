package org.lib;

public interface CommandIO {
    String nextLine();

    void print(String string);

    void printErr(Exception exception);
}
