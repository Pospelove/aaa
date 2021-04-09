package org.lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An implementation of data writer that use BufferedWriter
 */
public class BufferedDataWriter {
    public BufferedDataWriter(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void writeData(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        writer.write(data);
        writer.close();
    }

    private final String outputFileName;
}