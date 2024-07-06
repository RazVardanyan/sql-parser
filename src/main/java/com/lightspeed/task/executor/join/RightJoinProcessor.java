package com.lightspeed.task.executor.join;

import com.lightspeed.task.service.ConditionalTreeService;
import com.lightspeed.task.utils.Component;

import static com.lightspeed.task.constants.SqlParserConstants.RIGHT_JOIN;

@Component
public class RightJoinProcessor extends AbstractJoinProcessor {

    public RightJoinProcessor(ConditionalTreeService conditionalTreeService) {
        super(conditionalTreeService);
    }

    @Override
    public String getKeyword() {
        return RIGHT_JOIN;
    }
}
