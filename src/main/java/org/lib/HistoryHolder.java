package org.lib;

import java.util.LinkedList;
import java.util.List;

public class HistoryHolder {
    public HistoryHolder(int capacity) {
        this.capacity = capacity;
    }

    public void add(String command) {
        history.add(command);
        while (history.size() > this.capacity) {
            history.remove(0);
        }
    }

    public List<String> getHistory() {
        // defensive copying
        return new LinkedList<>(history);
    }

    private final int capacity;
    private final List<String> history = new LinkedList<>();
}
