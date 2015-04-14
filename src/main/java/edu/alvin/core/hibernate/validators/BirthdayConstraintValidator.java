package edu.alvin.core.hibernate.validators;

import org.hibernate.internal.util.StringHelper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class BirthdayConstraintValidator implements ConstraintValidator<Birthday, String> {
    private int minAge;
    private int maxAge;

    @Override
    public void initialize(Birthday constraintAnnotation) {
        minAge = constraintAnnotation.minAge();
        maxAge = constraintAnnotation.maxAge();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringHelper.isEmpty(value)) {
            return true;
        }
        DateTimeFormatter pattern = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC);
        LocalDateTime birthday, now = LocalDateTime.now(ZoneOffset.UTC);
        try {
            birthday = LocalDateTime.parse(value, pattern);
        } catch (DateTimeParseException e) {
            return false;
        }
        long years = now.until(birthday, ChronoUnit.YEARS);
        return years >= minAge && years <= maxAge;
    }
}
