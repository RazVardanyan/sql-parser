package com.lightspeed.task.service;

import com.lightspeed.task.query.Token;
import com.lightspeed.task.query.condition.AndCondition;
import com.lightspeed.task.query.condition.ComparisonOperator;
import com.lightspeed.task.query.condition.Condition;
import com.lightspeed.task.query.condition.OrCondition;
import com.lightspeed.task.strategy.LogicalConditionStrategy;
import com.lightspeed.task.strategy.StrategySupplier;
import com.lightspeed.task.utils.Component;
import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.*;
import static com.lightspeed.task.enums.ComparisonOperatorType.parseOperator;
import static com.lightspeed.task.enums.LogicalOperatorType.valueOf;
import static com.lightspeed.task.utils.SqlParserUtils.convertToColumn;
import static com.lightspeed.task.utils.ValueParserUtils.buildValueWrapper;
import static com.lightspeed.task.utils.ValueParserUtils.parseValue;

@Component
@RequiredArgsConstructor
public class ConditionalTreeService {
    private final StrategySupplier strategySupplier;

    public Condition buildTree(List<Token> tokens) {
        var deque = new ArrayDeque<Condition>();
        var operators = new ArrayDeque<String>();
        for (int i = 0; i < tokens.size(); i++) {
            var token = tokens.get(i);
            switch (token.getTokenType()) {
                case LOGICAL_OPERATOR -> {
                    LogicalConditionStrategy strategy;
                    if ((strategy = strategySupplier.supply(valueOf(token.getValue()))) != null) {
                        i = strategy.accept(tokens, i, deque);
                        break;
                    }
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.getValue())) {
                        handleConditional(operators.pop(), deque);
                    }
                    operators.push(token.getValue());
                }
                case BRACKET_OPEN -> operators.push(token.getValue());
                case BRACKET_CLOSE -> {
                    while (!BRACKET_OPENED_VALUE.equals(operators.peek())) {
                        handleConditional(operators.pop(), deque);
                    }
                    operators.pop();
                }
                case COMPARISON_OPERATOR -> {
                    var column = convertToColumn(tokens.get(i - 1).getValue());
                    var comparisonOperatorType = parseOperator(token.getValue());
                    var value = tokens.get(++i).getValue();
                    var parsedValue = parseValue(value);
                    if(!parsedValue.success()) {
                        parsedValue = buildValueWrapper(value, true);
                    }
                    deque.push(
                            ComparisonOperator.builder()
                                    .comparisonOperatorType(comparisonOperatorType)
                                    .column(column)
                                    .value(parsedValue)
                                    .build()
                    );
                }
            }
        }

        while (!operators.isEmpty()) {
            handleConditional(operators.pop(), deque);
        }

        return deque.pop();
    }

    private int precedence(String op) {
        return switch (op) {
            case AND -> 2;
            case OR -> 1;
            default -> 0;
        };
    }

    private Condition buildConditional(String op, Condition left, Condition right) {
        return switch (op) {
            case AND -> AndCondition.builder().left(left).right(right).build();
            case OR -> OrCondition.builder().left(left).right(right).build();
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }

    private void handleConditional(String op, Deque<Condition> deque) {
        var right = deque.pop();
        var left = deque.pop();
        deque.push(buildConditional(op, left, right));
    }
}
