package com.lightspeed.task.query.condition;

import com.lightspeed.task.utils.ValueWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BetweenCondition implements Condition {
    private String columnRef;
    private ValueWrapper<?> startValue;
    private ValueWrapper<?> endValue;
}
