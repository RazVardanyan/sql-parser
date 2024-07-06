package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.GROUP_BY;
import static com.lightspeed.task.enums.TokenType.IDENTIFIER;

@Component
public class GroupByProcessor implements SqlParseProcessor {

    @Override
    public void process(List<Token> tokens, Query query) {
        var groupBy = tokens.stream()
                .filter(t -> IDENTIFIER.equals(t.getTokenType()))
                .map(Token::getValue)
                .toList();
        query.setGroupByColumns(groupBy);
    }

    @Override
    public String getKeyword() {
        return GROUP_BY;
    }
}
