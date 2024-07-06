package com.lightspeed.task.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum ComparisonOperatorType {
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<=");
    private final String symbol;

    public static final Map<String, ComparisonOperatorType> OPERATOR_MAP;

    static {
        OPERATOR_MAP = Arrays.stream(values())
                .collect(Collectors.toMap(comparisonOperatorType -> comparisonOperatorType.symbol, Function.identity()));
    }

    public static ComparisonOperatorType parseOperator(String operatorToken) {
        var operator = OPERATOR_MAP.get(operatorToken);
        if (operator == null) {
            throw new IllegalArgumentException("Unknown comparisonOperatorType: " + operatorToken);
        }
        return operator;
    }
}