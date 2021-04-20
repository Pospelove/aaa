package org.lib;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class Reader {
    public abstract String readString(String s);

    public abstract void print(String str);

    public abstract void printErr(Exception exception);

    public <T extends Enum<T>> T readEnum(String variableName, Class<T> enumeration) {
        T res = null;
        while (res == null) {
            String tip = makeTip(variableName, joinEnumConstants(enumeration));
            print(tip);
            try {
                res = T.valueOf(enumeration, readString(variableName).toUpperCase());
            } catch (IllegalArgumentException e) {
                printErr(e);
            }
        }
        return res;
    }

    public Long readLong(String variableName, String condition, Function<Long, Boolean> isConditionMet) {
        // See 'readFloat'
        // TODO: Fix DRY violation as soon as third similar overload comes
        Long res = null;
        while (res == null || !isConditionMet.apply(res)) {
            print(makeTip(variableName, condition));
            try {
                res = Long.parseLong(readString(variableName));
            } catch (NumberFormatException e) {
                printErr(e);
            }
        }
        return res;
    }

    public Float readFloat(String variableName, String condition, Function<Float, Boolean> isConditionMet) {
        // See 'readLong'
        // TODO: Fix DRY violation as soon as third similar overload comes
        Float res = null;
        while (res == null || !isConditionMet.apply(res)) {
            print(makeTip(variableName, condition));
            try {
                res = Float.parseFloat(readString(variableName));
            } catch (NumberFormatException e) {
                printErr(e);
            }
        }
        return res;
    }

    // TicketType.class => "VIP | USUAL | BUDGETARY | CHEAP"
    public static <T extends Enum<T>> String joinEnumConstants(Class<T> enumeration) {
        Stream<T> stream = Arrays.stream(enumeration.getEnumConstants());
        return stream.map(Object::toString).reduce((x, y) -> x + " | " + y).orElse("");
    }

    static protected String makeTip(String variableName) {
        return makeTip(variableName, "");
    }

    static protected String makeTip(String variableName, String condition) {
        String res = variableName;
        if (condition.length() > 0) {
            res += " (" + condition + ")";
        }
        res += ": ";
        return res;
    }
}
