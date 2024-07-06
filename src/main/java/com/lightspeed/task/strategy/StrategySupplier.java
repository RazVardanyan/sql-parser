package com.lightspeed.task.strategy;

import com.lightspeed.task.enums.LogicalOperatorType;
import com.lightspeed.task.utils.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.lightspeed.task.utils.ComponentContainer.get;

@Component
public class StrategySupplier {
    private final Map<LogicalOperatorType, LogicalConditionStrategy> strategyMap;

    public StrategySupplier() {
        var strategies = initStrategies();
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(LogicalConditionStrategy::getType, Function.identity()));
    }

    private List<LogicalConditionStrategy> initStrategies() {
        return List.of(
                get(InConditionStrategy.class),
                get(LikeConditionStrategy.class),
                get(BetweenConditionStrategy.class)
        );
    }

    public LogicalConditionStrategy supply(LogicalOperatorType opType) {
        return strategyMap.get(opType);
    }
}
