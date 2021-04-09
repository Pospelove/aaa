package org.lib;

import org.models.Ticket;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MyCollection {
    private PriorityQueue<Ticket> data = new PriorityQueue<>();
    private final Date initializationDate = new Date();

    public String getType() {
        return data.getClass().getSimpleName();
    }

    public Date getInitializationDate() {
        return initializationDate;
    }

    public int getSize() {
        return data.size();
    }

    public long getFreeId() {
        long maxId = -1;
        for (Ticket element : data) {
            long id = element.getId();
            if (maxId < id) {
                maxId = id;
            }
        }
        return maxId + 1;
    }

    public void insert(Ticket element) {
        data.add(element);
    }

    public void forEach(Consumer<Ticket> visitor) {
        data.forEach(visitor);
    }

    public Stream<Ticket> stream() {
        return data.stream();
    }

    public void update(Ticket update) {
        PriorityQueue<Ticket> res = new PriorityQueue<>();
        for (Ticket element : data) {
            if (!element.getId().equals(update.getId())) {
                res.add(element);
            }
        }
        res.add(update);
        data = res;
    }

    public int remove(Long id) {
        int n = 0;
        PriorityQueue<Ticket> res = new PriorityQueue<>();
        for (Ticket element : data) {
            if (!element.getId().equals(id)) {
                res.add(element);
            } else {
                ++n;
            }
        }
        data = res;
        return n;
    }

    public void clear() {
        data.clear();
    }
}
