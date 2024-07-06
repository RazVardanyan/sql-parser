package com.lightspeed.task.executor.join;

import com.lightspeed.task.executor.SqlParseProcessor;
import com.lightspeed.task.query.Join;
import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Source;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.service.ConditionalTreeService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.ON;
import static com.lightspeed.task.enums.TokenType.IDENTIFIER;
import static com.lightspeed.task.utils.SqlParserUtils.*;

@RequiredArgsConstructor
public abstract class AbstractJoinProcessor implements SqlParseProcessor {
    protected final ConditionalTreeService conditionalTreeService;

    @Override
    public void process(List<Token> tokens, Query query) {
        var joins = new ArrayList<Join>();
        var joinType = mapToType(this.getKeyword());
        var isParsingCondition = false;
        Source currentSource = null;
        var conditionTokens = new ArrayList<Token>();

        for (var token : tokens) {
            if (IDENTIFIER.equals(token.getTokenType()) && ON.equalsIgnoreCase(token.getValue())) {
                isParsingCondition = true;
                if (currentSource != null && !conditionTokens.isEmpty()) {
                    joins.add(Join.builder()
                            .type(joinType)
                            .fromItem(currentSource)
                            .condition(conditionalTreeService.buildTree(conditionTokens))
                            .build());
                    conditionTokens.clear();
                }
            } else {
                if (isParsingCondition) {
                    conditionTokens.add(token);
                } else {
                    if (currentSource == null) {
                        currentSource = Source.builder().tableName(token.getValue()).build();
                    } else {
                        currentSource.setAlias(token.getValue());
                    }
                }
            }
        }

        if (currentSource != null && !conditionTokens.isEmpty()) {
            joins.add(Join.builder()
                    .type(joinType)
                    .fromItem(currentSource)
                    .condition(conditionalTreeService.buildTree(conditionTokens))
                    .build());
        }
        var actualJoins = query.getJoins();
        actualJoins.addAll(joins);
        query.setJoins(actualJoins);
    }
}
