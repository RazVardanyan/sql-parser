package com.lightspeed.task.executor.join;

import com.lightspeed.task.service.ConditionalTreeService;
import com.lightspeed.task.utils.Component;

import static com.lightspeed.task.constants.SqlParserConstants.LEFT_JOIN;

@Component
public class LeftJoinProcessor extends AbstractJoinProcessor {

    public LeftJoinProcessor(ConditionalTreeService conditionalTreeService) {
        super(conditionalTreeService);
    }

    @Override
    public String getKeyword() {
        return LEFT_JOIN;
    }
}
