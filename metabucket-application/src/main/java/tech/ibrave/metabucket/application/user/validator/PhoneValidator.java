package tech.ibrave.metabucket.application.user.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Author: anct
 * Date: 08/06/2023
 * #YWNA
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern[] patterns = {
            Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"),
            Pattern.compile("^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"),
            Pattern.compile("^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    };
    private boolean ignoreIfEmpty;

    @Override
    public void initialize(Phone phone) {
        this.ignoreIfEmpty = phone.ignoreIfEmpty();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ignoreIfEmpty) {
            return true;
        }

        for (var pattern : patterns) {
            if (pattern.matcher(value).matches()) {
                return true;
            }
        }

        return false;
    }
}
