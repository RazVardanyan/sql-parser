package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.service.ConditionalTreeService;
import com.lightspeed.task.utils.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.WHERE;

@Component
@RequiredArgsConstructor
public class WhereProcessor implements SqlParseProcessor {
    private final ConditionalTreeService conditionalTreeService;

    @Override
    public void process(List<Token> tokens, Query query) {
        query.setWhere(conditionalTreeService.buildTree(tokens));
    }

    @Override
    public String getKeyword() {
        return WHERE;
    }
}
