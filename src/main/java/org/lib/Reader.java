package org.lib;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Reader {
    public Reader(CommandIO io) {
        this.io = io;
    }

    public String readString(String variableName) {
        String str = "";
        while (str.isEmpty()) {
            io.getPrintStream().print(makeTip(variableName));
            str = io.getScanner().nextLine();
        }
        return str;
    }

    public <T extends Enum<T>> T readEnum(String variableName, Class<T> enumeration) {
        T res = null;
        while (res == null) {
            String tip = makeTip(variableName, joinEnumConstants(enumeration));
            io.getPrintStream().print(tip);
            try {
                res = T.valueOf(enumeration, io.getScanner().nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                io.getPrintStream().println(e.toString());
            }
        }
        return res;
    }

    public Long readLong(String variableName, String condition, Function<Long, Boolean> isConditionMet) {
        // See 'readFloat'
        // TODO: Fix DRY violation as soon as third similar overload comes
        Long res = null;
        while (res == null || !isConditionMet.apply(res)) {
            io.getPrintStream().print(makeTip(variableName, condition));
            try {
                res = Long.parseLong(io.getScanner().nextLine());
            } catch (NumberFormatException e) {
                io.getPrintStream().println(e.toString());
            }
        }
        return res;
    }

    public Float readFloat(String variableName, String condition, Function<Float, Boolean> isConditionMet) {
        // See 'readLong'
        // TODO: Fix DRY violation as soon as third similar overload comes
        Float res = null;
        while (res == null || !isConditionMet.apply(res)) {
            io.getPrintStream().print(makeTip(variableName, condition));
            try {
                res = Float.parseFloat(io.getScanner().nextLine());
            } catch (NumberFormatException e) {
                io.getPrintStream().println(e.toString());
            }
        }
        return res;
    }

    // TicketType.class => "VIP | USUAL | BUDGETARY | CHEAP"
    static public <T extends Enum<T>> String joinEnumConstants(Class<T> enumeration) {
        Stream<T> stream = Arrays.stream(enumeration.getEnumConstants());
        return stream.map(Object::toString).reduce((x, y) -> x + " | " + y).orElse("");
    }

    private String makeTip(String variableName) {
        return makeTip(variableName, "");
    }

    private String makeTip(String variableName, String condition) {
        String res = variableName;
        if (condition.length() > 0) {
            res += " (" + condition + ")";
        }
        res += ": ";
        return res;
    }

    private final CommandIO io;
}
