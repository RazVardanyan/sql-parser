package com.lightspeed.task.utils;

import com.lightspeed.task.enums.AggregateFunction;
import com.lightspeed.task.enums.JoinType;
import com.lightspeed.task.enums.SortOrder;
import com.lightspeed.task.query.Column;
import com.lightspeed.task.query.Source;
import com.lightspeed.task.query.Token;
import net.sf.jsqlparser.util.validation.ValidationError;

import java.util.List;
import java.util.regex.Pattern;

import static com.lightspeed.task.constants.SqlParserConstants.*;
import static com.lightspeed.task.enums.AggregateFunction.NONE;
import static com.lightspeed.task.enums.AggregateFunction.valueOf;
import static com.lightspeed.task.enums.JoinType.*;
import static com.lightspeed.task.enums.SortOrder.ASC;
import static com.lightspeed.task.enums.SortOrder.DESC;
import static com.lightspeed.task.enums.TokenType.IDENTIFIER;
import static com.lightspeed.task.enums.TokenType.LITERAL;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

public final class SqlParserUtils {
    private SqlParserUtils() {
    }

    public static String formattedList(List<ValidationError> validationErrors) {
        return validationErrors.stream()
                .map(ValidationError::getStatements)
                .reduce("\n", String::join);
    }

    public static Pattern compile(String regex) {
        return Pattern.compile(regex, CASE_INSENSITIVE);
    }

    public static Column convertToColumn(String columnRef) {
        var matcher = compile("(\\b\\w+)\\((\\w+(?:\\.\\w+)?|\\*)\\)").matcher(columnRef);
        return matcher.matches()
                ? buildColumn(valueOf(matcher.group(1).toUpperCase()), matcher.group(2))
                : buildColumn(NONE, columnRef);
    }

    private static Column buildColumn(AggregateFunction function, String columnRef) {
        return Column.builder()
                .aggregateFunction(function)
                .columnRef(columnRef)
                .build();
    }

    public static int singleValueParser(List<Token> tokens) {
        return tokens.stream()
                .filter(t -> LITERAL.equals(t.getTokenType()))
                .map(t -> Integer.parseInt(t.getValue()))
                .findFirst().orElse(0);
    }

    public static SortOrder order(String order) {
        return DESC.name().equalsIgnoreCase(order) ? DESC : ASC;
    }

    public static Source parseSource(List<Token> tokens, int i) {
        var sourceBuilder = Source.builder().tableName(tokens.get(i).getValue());
        if (isSafeIteration(tokens.size(), i) && IDENTIFIER.equals(tokens.get(++i).getTokenType())) {
            sourceBuilder.alias(tokens.get(i).getValue());
        }
        return sourceBuilder.build();
    }

    public static boolean isSafeIteration(int size, int index) {
        return index + 1 < size;
    }

    public static JoinType mapToType(String keyword) {
        return switch (keyword) {
            case INNER_JOIN -> INNER;
            case LEFT_JOIN -> LEFT;
            case RIGHT_JOIN -> RIGHT;
            case FULL_JOIN -> FULL;
            default -> throw new IllegalStateException("Unexpected value: " + keyword);
        };
    }
}
