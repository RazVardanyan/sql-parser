package com.lightspeed.task.validation;

import com.lightspeed.task.utils.Component;
import net.sf.jsqlparser.util.validation.Validation;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

import static com.lightspeed.task.utils.SqlParserUtils.formattedList;
import static net.sf.jsqlparser.util.validation.feature.FeaturesAllowed.SELECT;

@Component
public class SelectSyntaxValidation implements SyntaxValidation {

    @Override
    public void validate(String query) throws SQLSyntaxErrorException {
        var validationErrors = Validation.validate(List.of(SELECT), query);
        if (!validationErrors.isEmpty())
            throw new SQLSyntaxErrorException(formattedList(validationErrors));
    }
}
