package com.lightspeed.task.service;

import com.lightspeed.task.enums.TokenType;
import com.lightspeed.task.query.Token;
import com.lightspeed.task.utils.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.lightspeed.task.constants.SqlParserConstants.*;
import static com.lightspeed.task.enums.TokenType.*;

@Component
public class SqlTokenizationService {

    public Map<String, List<Token>> tokenizeAndCategorize(String query) {
        var tokens = this.tokenize(query);
        return categorize(tokens);
    }

    private Map<String, List<Token>> categorize(List<Token> tokens) {
        var value = new AtomicReference<String>(null);
        return tokens.stream()
                .filter(token -> {
                    if (KEYWORD.equals(token.getTokenType())) {
                        value.set(token.getValue());
                    }
                    return value.get() != null && !KEYWORD.equals(token.getTokenType());
                }).collect(Collectors.groupingBy(token -> value.get()));
    }


    public List<Token> tokenize(String query) {
        var tokens = new ArrayList<Token>();
        var matcher = TOKEN_PATTERN.matcher(query);

        while (matcher.find()) {
            var tokenValue = matcher.group();
            var tokenType = determineTokenType(tokenValue);
            tokens.add(new Token(tokenType, tokenValue));
        }

        return tokens;
    }

    private TokenType determineTokenType(String tokenValue) {
        if (tokenValue.matches(KEYWORDS)) {
            return TokenType.KEYWORD;
        } else if (tokenValue.matches(LOGICAL_OPERATORS)) {
            return TokenType.LOGICAL_OPERATOR;
        } else if (tokenValue.matches(AGGREGATIONS) || tokenValue.matches(IDENTIFIERS)) {
            return TokenType.IDENTIFIER;
        } else if (tokenValue.matches(LITERALS)) {
            return TokenType.LITERAL;
        } else if (tokenValue.matches(COMPARISON_OPERATORS)) {
            return TokenType.COMPARISON_OPERATOR;
        } else if (tokenValue.matches(SEPARATORS)) {
            return TokenType.SEPARATOR;
        } else if (tokenValue.matches("\\(")) {
            return TokenType.BRACKET_OPEN;
        } else if (tokenValue.matches("\\)")) {
            return TokenType.BRACKET_CLOSE;
        } else {
            return TokenType.UNKNOWN;
        }
    }

}
