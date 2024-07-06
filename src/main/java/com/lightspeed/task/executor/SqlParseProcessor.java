package com.lightspeed.task.executor;

import com.lightspeed.task.query.Query;
import com.lightspeed.task.query.Token;

import java.util.List;

public interface SqlParseProcessor {
    void process(List<Token> tokens, Query query);

    String getKeyword();
}
