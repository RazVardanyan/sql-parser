package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.SelectItem;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lightspeed.task.constants.SqlParserConstants.*;
import static com.lightspeed.task.enums.TokenType.IDENTIFIER;
import static com.lightspeed.task.utils.SqlParserUtils.convertToColumn;

@Component
public class SelectItemProcessor implements SqlParseProcessor {

    @Override
    public void process(List<Token> tokens, Query query) {
        if (DISTINCT.equalsIgnoreCase(tokens.get(0).getValue())) {
            query.setDistinct(true);
        }
        var selectItems = new ArrayList<SelectItem>();
        int size = tokens.size();

        for (int i = 0; i < size; i++) {
            var token = tokens.get(i);
            if (IDENTIFIER.equals(token.getTokenType())) {
                var selectItemBuilder = SelectItem.builder().column(convertToColumn(token.getValue()));
                if (i + 2 < size && AS.equalsIgnoreCase(tokens.get(i + 1).getValue())) {
                    selectItemBuilder.alias(tokens.get(i + 2).getValue());
                    i += 2;
                }
                selectItems.add(selectItemBuilder.build());
            }
        }
        query.setSelectItems(selectItems);
    }

    @Override
    public String getKeyword() {
        return SELECT;
    }
}
