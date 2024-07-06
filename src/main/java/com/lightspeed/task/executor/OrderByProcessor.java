package com.lightspeed.task.executor;

import com.lightspeed.task.query.OrderBy;
import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.ORDER_BY;
import static com.lightspeed.task.enums.TokenType.IDENTIFIER;
import static com.lightspeed.task.utils.SqlParserUtils.order;

@Component
public class OrderByProcessor implements SqlParseProcessor {

    @Override
    public void process(List<Token> tokens, Query query) {
        var orderByList = new ArrayList<OrderBy>();
        for (int i = 0; i < tokens.size(); i++) {
            var token = tokens.get(i);
            if(IDENTIFIER.equals(token.getTokenType())) {
                orderByList.add(OrderBy.builder()
                        .column(token.getValue())
                        .order(order(tokens.get(++i).getValue()))
                        .build()
                );
            }
        }
        query.setOrderByColumns(orderByList);
    }

    @Override
    public String getKeyword() {
        return ORDER_BY;
    }
}
