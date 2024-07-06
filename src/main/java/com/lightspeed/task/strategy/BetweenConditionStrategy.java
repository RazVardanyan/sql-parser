package com.lightspeed.task.strategy;

import com.lightspeed.task.enums.LogicalOperatorType;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.query.condition.BetweenCondition;
import com.lightspeed.task.query.condition.Condition;
import com.lightspeed.task.utils.Component;

import java.util.Deque;
import java.util.List;

import static com.lightspeed.task.enums.LogicalOperatorType.BETWEEN;
import static com.lightspeed.task.utils.ValueParserUtils.parseValue;

@Component
public class BetweenConditionStrategy implements LogicalConditionStrategy {
    @Override
    public int accept(List<Token> tokens, int index, Deque<Condition> deque) {
        var columnRef = tokens.get(index - 1).getValue();
        var startValue = tokens.get(++index).getValue();
        var endValue = tokens.get(index += 2).getValue();
        deque.push(BetweenCondition.builder()
                .columnRef(columnRef)
                .startValue(parseValue(startValue))
                .endValue(parseValue(endValue))
                .build()
        );
        return index;
    }

    @Override
    public LogicalOperatorType getType() {
        return BETWEEN;
    }
}
