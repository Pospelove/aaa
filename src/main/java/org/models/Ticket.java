package org.models;

import org.lib.Reader;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class Ticket implements Comparable<Ticket>, Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Event event; //Поле не может быть null
    private TicketType type; //Поле не может быть null
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long price = 0; //Значение поля должно быть больше 0
    private Long discount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100

    public Ticket(long id, Reader reader) {
        this.id = id;
        creationDate = new Date();

        name = reader.readString("Name");
        coordinates = new Coordinates(reader);
        type = reader.readEnum("Type", TicketType.class);
        price = reader.readLong("Price", ">0", x -> x > 0);
        discount = reader.readLong("Discount", ">0 & <= 100", x -> x > 0 && x <= 100);
        event = new Event((int) id, reader);
    }

    public Long getId() {
        return id;
    }

    public Long getDiscount() {
        return discount;
    }

    public TicketType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", event=" + event +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    @Override
    public int compareTo(Ticket ticket) {
        try {
            return Arrays.compare(toByteArray(this), toByteArray(ticket));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket cloneWithoutIdsAndDates() {
        try {
            Ticket clone = (Ticket) fromByteArray(toByteArray(this));
            clone.id = 0L;
            clone.event.id = 0;
            clone.creationDate = new Date(0);
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] toByteArray(Serializable serializable) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
        objStream.writeObject(serializable);
        objStream.close();

        return byteStream.toByteArray();
    }

    private static Serializable fromByteArray(byte[] b) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        ObjectInput in = new ObjectInputStream(bis);
        return (Serializable) in.readObject();
    }
}