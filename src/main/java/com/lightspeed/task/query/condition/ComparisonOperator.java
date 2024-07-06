package com.lightspeed.task.query.condition;

import com.lightspeed.task.enums.ComparisonOperatorType;
import com.lightspeed.task.query.Column;
import com.lightspeed.task.query.condition.Condition;
import com.lightspeed.task.utils.ValueWrapper;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonOperator implements Condition {
    private ComparisonOperatorType comparisonOperatorType;
    private Column column;
    private ValueWrapper<?> value;
}
