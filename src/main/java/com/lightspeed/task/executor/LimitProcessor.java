package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.LIMIT;
import static com.lightspeed.task.utils.SqlParserUtils.singleValueParser;

@Component
public class LimitProcessor implements SqlParseProcessor {

    @Override
    public void process(List<Token> tokens, Query query) {
        query.setLimit(singleValueParser(tokens));
    }

    @Override
    public String getKeyword() {
        return LIMIT;
    }
}
