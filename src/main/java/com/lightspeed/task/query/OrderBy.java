package com.lightspeed.task.query;

import com.lightspeed.task.enums.SortOrder;
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
public class OrderBy {
    private String column;
    private SortOrder order;
}
