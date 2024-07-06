package com.lightspeed.task.query.condition;

import com.lightspeed.task.utils.ValueWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InCondition implements Condition {
    private String columnRef;
    private List<ValueWrapper<?>> values;
}
