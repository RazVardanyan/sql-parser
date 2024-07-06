package com.lightspeed.task.query;

import com.lightspeed.task.enums.AggregateFunction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Column {
    private AggregateFunction aggregateFunction;
    private String columnRef;
}
