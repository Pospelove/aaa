package org.commands;

public class BadCommandArgumentException extends Exception {
    public BadCommandArgumentException(String expected, String actual) {
        super("Expected command argument to be " + expected + ", but got " + actual);
    }
}