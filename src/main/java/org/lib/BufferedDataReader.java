package org.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * An implementation of data reader that use BufferedReader
 */
public class BufferedDataReader {
    public BufferedDataReader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public String readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line;
        StringBuilder res = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            res.append(line).append("\n");
        }
        return res.toString();
    }

    private final String inputFileName;
}