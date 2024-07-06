package com.lightspeed.task.facade;

import com.lightspeed.task.service.SqlTokenizationService;
import com.lightspeed.task.executor.SqlParserProcessorManager;
import com.lightspeed.task.query.Query;
import com.lightspeed.task.validation.SyntaxValidation;
import lombok.RequiredArgsConstructor;

import java.sql.SQLSyntaxErrorException;

@RequiredArgsConstructor
public class SqlParserFacade {
    private final SyntaxValidation syntaxValidation;
    private final SqlTokenizationService tokenizationService;
    private final SqlParserProcessorManager sqlParserProcessorManager;

    public Query parse(String query) {
        try {
            syntaxValidation.validate(query);
        }
        catch (SQLSyntaxErrorException e) {
            throw new IllegalArgumentException("Query syntax error: ", e);
        }
        var tokens = tokenizationService.tokenizeAndCategorize(query);
        return sqlParserProcessorManager.parse(tokens);
    }
}
