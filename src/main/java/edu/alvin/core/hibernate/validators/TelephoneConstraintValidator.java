package edu.alvin.core.hibernate.validators;

import org.hibernate.internal.util.StringHelper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class TelephoneConstraintValidator implements ConstraintValidator<Telephone, String> {

    private final Pattern pattern = Pattern.compile("^\\(?0\\d{2}[\\s)-]?\\d{8}$|" +
            "^\\(?0\\d{3}[\\s)-]?\\d{7}$|^0?1\\d{10}$");

    @Override
    public void initialize(Telephone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringHelper.isEmpty(value) || pattern.matcher(value).matches();
    }
}
