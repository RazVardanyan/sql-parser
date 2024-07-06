package com.lightspeed.task.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lightspeed.task.enums.JoinType;
import com.lightspeed.task.query.condition.Condition;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Join {
    private JoinType type;
    private Source fromItem;
    private Condition condition;
}
