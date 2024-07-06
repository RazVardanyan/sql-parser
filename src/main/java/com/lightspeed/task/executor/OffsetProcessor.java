package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.OFFSET;
import static com.lightspeed.task.utils.SqlParserUtils.singleValueParser;

@Component
public class OffsetProcessor implements SqlParseProcessor {

    @Override
    public void process(List<Token> tokens, Query query) {
        query.setOffset(singleValueParser(tokens));
    }

    @Override
    public String getKeyword() {
        return OFFSET;
    }
}
