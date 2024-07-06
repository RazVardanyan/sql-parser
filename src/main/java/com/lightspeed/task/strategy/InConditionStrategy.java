package com.lightspeed.task.strategy;

import com.lightspeed.task.enums.LogicalOperatorType;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.query.condition.Condition;
import com.lightspeed.task.query.condition.InCondition;
import com.lightspeed.task.utils.Component;
import com.lightspeed.task.utils.ValueWrapper;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static com.lightspeed.task.enums.LogicalOperatorType.IN;
import static com.lightspeed.task.utils.ValueParserUtils.parseValue;

@Component
public class InConditionStrategy implements LogicalConditionStrategy {

    @Override
    public int accept(List<Token> tokens, int index, Deque<Condition> deque) {
        var columnRef = tokens.get(index - 1).getValue();
        var values = new ArrayList<ValueWrapper<?>>();
        int i = index;
        for (; i < tokens.size(); i += 2) {
            values.add(parseValue(tokens.get(i).getValue()));
        }
        deque.add(
                InCondition.builder()
                        .columnRef(columnRef)
                        .values(values)
                        .build()
        );
        return i;
    }

    @Override
    public LogicalOperatorType getType() {
        return IN;
    }
}
