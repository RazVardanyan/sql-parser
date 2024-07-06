package com.lightspeed.task.executor.join;

import com.lightspeed.task.service.ConditionalTreeService;
import com.lightspeed.task.utils.Component;

import static com.lightspeed.task.constants.SqlParserConstants.FULL_JOIN;

@Component
public class FullJoinProcessor extends AbstractJoinProcessor {

    public FullJoinProcessor(ConditionalTreeService conditionalTreeService) {
        super(conditionalTreeService);
    }

    @Override
    public String getKeyword() {
        return FULL_JOIN;
    }
}
