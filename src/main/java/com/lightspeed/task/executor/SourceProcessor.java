package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Source;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.FROM;
import static com.lightspeed.task.enums.TokenType.IDENTIFIER;
import static com.lightspeed.task.utils.SqlParserUtils.parseSource;

@Component
public class SourceProcessor implements SqlParseProcessor {

    @Override
    public void process(List<Token> tokens, Query query) {
        var sources = new ArrayList<Source>();
        var size = tokens.size();

        for (int i = 0; i < size; i++) {
            var token = tokens.get(i);
            if (IDENTIFIER.equals(token.getTokenType())) {
                sources.add(parseSource(tokens, i));
                i++;
            }
        }
        query.setSource(sources);
    }

    @Override
    public String getKeyword() {
        return FROM;
    }

}
