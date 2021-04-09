package org.models;

import org.lib.Reader;

import java.io.Serializable;

public class Event implements Serializable {
    protected Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final String description; //Строка не может быть пустой, Поле не может быть null
    private final EventType eventType; //Поле может быть null

    public Event(Integer id, Reader reader) {
        this.id = id;
        name = reader.readString("Event.Name");
        description = reader.readString("Event.Description");
        eventType = reader.readEnum("Event.Type", EventType.class);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventType=" + eventType +
                '}';
    }
}