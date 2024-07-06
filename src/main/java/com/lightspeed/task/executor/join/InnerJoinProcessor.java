package com.lightspeed.task.executor.join;

import com.lightspeed.task.service.ConditionalTreeService;
import com.lightspeed.task.utils.Component;

import static com.lightspeed.task.constants.SqlParserConstants.INNER_JOIN;

@Component
public class InnerJoinProcessor extends AbstractJoinProcessor {

    public InnerJoinProcessor(ConditionalTreeService conditionalTreeService) {
        super(conditionalTreeService);
    }

    @Override
    public String getKeyword() {
        return INNER_JOIN;
    }
}
