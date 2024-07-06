package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.service.ConditionalTreeService;
import com.lightspeed.task.utils.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.HAVING;

@Component
@RequiredArgsConstructor
public class HavingProcessor implements SqlParseProcessor {
    private final ConditionalTreeService conditionalTreeBuilder;

    @Override
    public void process(List<Token> tokens, Query query) {
        query.setHaving(conditionalTreeBuilder.buildTree(tokens));
    }

    @Override
    public String getKeyword() {
        return HAVING;
    }
}
