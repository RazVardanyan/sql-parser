package com.lightspeed.task.constants;

import java.util.regex.Pattern;

import static com.lightspeed.task.utils.SqlParserUtils.compile;

public final class SqlParserConstants {
    private SqlParserConstants() {
    }

    public static final String ON = "ON";
    public static final String BRACKET_OPENED_VALUE = "(";

    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";
    public static final String RIGHT_JOIN = "RIGHT JOIN";
    public static final String LEFT_JOIN = "LEFT JOIN";
    public static final String INNER_JOIN = "INNER JOIN";
    public static final String FULL_JOIN = "FULL JOIN";
    public static final String GROUP_BY = "GROUP BY";
    public static final String ORDER_BY = "ORDER BY";
    public static final String HAVING = "HAVING";
    public static final String LIMIT = "LIMIT";
    public static final String OFFSET = "OFFSET";
    public static final String OR = "OR";
    public static final String AND = "AND";
    public static final String AS = "AS";
    public static final String DISTINCT = "DISTINCT";

    public static final String KEYWORDS = "\\b(SELECT|WHERE|FROM|RIGHT JOIN|LEFT JOIN|INNER JOIN|FULL JOIN|GROUP BY|ORDER BY|HAVING|LIMIT|OFFSET)\\b";
    public static final String LOGICAL_OPERATORS = "\\b(AND|OR|IN|BETWEEN|LIKE)\\b";
    public static final String IDENTIFIERS = "\\*|[a-zA-Z_][a-zA-Z0-9_\\.]*";
    public static final String AGGREGATIONS = "\\b(COUNT|SUM|AVG|MIN|MAX)\\b\\s*\\(.*?\\)";
    public static final String LITERALS = "'[^']*'|\\d+|TRUE|FALSE|\\b\\d{1,2} [A-Za-z]+ \\d{4}\\b";
    public static final String COMPARISON_OPERATORS = "=|!=|>|<|<=|>=";
    public static final String SEPARATORS = ",|;";
    public static final String BRACKETS = "\\(|\\)";

    public static final Pattern TOKEN_PATTERN = compile(
            String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                    KEYWORDS,
                    LOGICAL_OPERATORS,
                    AGGREGATIONS,
                    IDENTIFIERS,
                    LITERALS,
                    COMPARISON_OPERATORS,
                    SEPARATORS,
                    BRACKETS)
    );
}
