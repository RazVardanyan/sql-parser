package com.lightspeed.task.strategy;

import com.lightspeed.task.enums.LogicalOperatorType;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.query.condition.Condition;

import java.util.Deque;
import java.util.List;

public interface LogicalConditionStrategy {
    /**
     * @param tokens stream of sql tokens
     * @param index  beginning of condition in tokens
     * @param deque  conditional values deque
     * @return index from where the condition logic should continue
     */
    int accept(List<Token> tokens, int index, Deque<Condition> deque);

    LogicalOperatorType getType();
}
