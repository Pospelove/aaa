package org.models;

import org.lib.Reader;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final Float x; //Поле не может быть null
    private final long y; //Значение поля должно быть больше -494

    public Coordinates(Reader reader) {
        x = reader.readFloat("Coordinates.X", "", (x) -> true);
        y = reader.readLong("Coordinates.Y", ">-494", (x) -> x > -494);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}