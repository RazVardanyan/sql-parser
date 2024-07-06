package com.lightspeed.task.query;

import com.lightspeed.task.query.condition.Condition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Query {
    private List<SelectItem> selectItems;
    private boolean isDistinct;
    private List<Source> source;
    private Condition where;
    private List<Join> joins;
    private List<String> groupByColumns;
    private Condition having;
    private List<OrderBy> orderByColumns;
    private int limit;
    private int offset;
    private Query subQuery;

    public List<Join> getJoins() {
        if (this.joins == null)
            return new ArrayList<>();
        return this.joins;
    }
}