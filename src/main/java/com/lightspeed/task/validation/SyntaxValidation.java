package com.lightspeed.task.validation;

import java.sql.SQLSyntaxErrorException;

public interface SyntaxValidation {
    void validate(String query) throws SQLSyntaxErrorException;
}
