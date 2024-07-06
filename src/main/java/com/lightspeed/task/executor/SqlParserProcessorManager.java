package com.lightspeed.task.executor;

import com.lightspeed.task.executor.join.FullJoinProcessor;
import com.lightspeed.task.executor.join.InnerJoinProcessor;
import com.lightspeed.task.executor.join.LeftJoinProcessor;
import com.lightspeed.task.executor.join.RightJoinProcessor;
import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.List;
import java.util.Map;

import static com.lightspeed.task.utils.ComponentContainer.get;

@Component
public class SqlParserProcessorManager {

    private final List<SqlParseProcessor> sqlParseProcessors;

    public SqlParserProcessorManager() {
        sqlParseProcessors = List.of(
                get(SelectItemProcessor.class),
                get(SourceProcessor.class),
                get(InnerJoinProcessor.class),
                get(LeftJoinProcessor.class),
                get(RightJoinProcessor.class),
                get(FullJoinProcessor.class),
                get(WhereProcessor.class),
                get(GroupByProcessor.class),
                get(HavingProcessor.class),
                get(OrderByProcessor.class),
                get(LimitProcessor.class),
                get(OffsetProcessor.class)
        );
    }

    public Query parse(Map<String, List<Token>> tokenizedQuery) {
        var query = Query.builder().build();
        sqlParseProcessors.stream()
                .filter(processor -> tokenizedQuery.get(processor.getKeyword()) != null)
                .forEach(processor -> processor.process(tokenizedQuery.get(processor.getKeyword()), query));
        return query;
    }

}
