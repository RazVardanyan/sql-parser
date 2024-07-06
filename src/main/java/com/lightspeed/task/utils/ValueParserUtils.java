package com.lightspeed.task.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.time.LocalDate.parse;
import static org.apache.commons.lang3.StringUtils.strip;

public final class ValueParserUtils {

    private ValueParserUtils() {
    }

    private static ValueWrapper<Integer> tryParseInteger(String str) {
        try {
            return buildValueWrapper(parseInt(str), true);
        }
        catch (NumberFormatException e) {
            return buildValueWrapper(null, false);
        }
    }

    private static ValueWrapper<Double> tryParseDouble(String str) {
        try {
            return buildValueWrapper(parseDouble(str), true);
        }
        catch (NumberFormatException e) {
            return buildValueWrapper(null, false);
        }
    }

    private static ValueWrapper<Boolean> tryParseBoolean(String str) {
        return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)
                ? buildValueWrapper(parseBoolean(str), true) : buildValueWrapper(null, false);
    }

    private static ValueWrapper<Character> tryParseChar(String str) {
        return str.length() == 1 ? buildValueWrapper(str.charAt(0), true) : buildValueWrapper(null, false);
    }

    private static ValueWrapper<LocalDate> tryParseDate(String str) {
        var dateStr = tryParseString(str);
        try {
            return dateStr.success() ? buildValueWrapper(parse(dateStr.value()), true) : buildValueWrapper(null, false);
        }
        catch (DateTimeParseException e) {
            return buildValueWrapper(null, false);
        }
    }

    private static ValueWrapper<String> tryParseString(String str) {
        return str.startsWith("'") && str.endsWith("'")
                ? buildValueWrapper(strip(str, "'"), true) : buildValueWrapper(null, false);
    }

    private static List<Function<String, ValueWrapper<?>>> getParsers() {
        return List.of(
                ValueParserUtils::tryParseInteger,
                ValueParserUtils::tryParseDouble,
                ValueParserUtils::tryParseBoolean,
                ValueParserUtils::tryParseChar,
                ValueParserUtils::tryParseDate,
                ValueParserUtils::tryParseString
        );
    }

    public static ValueWrapper<?> parseValue(String value) {
        return getParsers().stream()
                .map(p -> p.apply(value))
                .filter(ValueWrapper::success)
                .findAny().orElse(buildValueWrapper(null, false));
    }

    public static <T> ValueWrapper<T> buildValueWrapper(T value, boolean success) {
        return ValueWrapper.<T>builder()
                .value(value)
                .success(success)
                .build();
    }

}
