package com.lightspeed.task.strategy;

import com.lightspeed.task.enums.LogicalOperatorType;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.query.condition.Condition;
import com.lightspeed.task.query.condition.LikeCondition;
import com.lightspeed.task.utils.Component;

import java.util.Deque;
import java.util.List;

import static com.lightspeed.task.enums.LogicalOperatorType.LIKE;

@Component
public class LikeConditionStrategy implements LogicalConditionStrategy {

    @Override
    public int accept(List<Token> tokens, int index, Deque<Condition> deque) {
        var columnRef = tokens.get(index - 1).getValue();
        var value = tokens.get(index++).getValue();
        deque.add(
                LikeCondition.builder()
                        .columnRef(columnRef)
                        .pattern(value)
                        .build()
        );
        return index;
    }

    @Override
    public LogicalOperatorType getType() {
        return LIKE;
    }
}
