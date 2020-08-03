package com.mkostadinov.eticketbackend.annotation.validators;

import com.mkostadinov.eticketbackend.annotation.IsAdult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<IsAdult, LocalDateTime> {

    @Override
    public void initialize(IsAdult constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime ld = LocalDateTime.now();
        ld = ld.minusYears(18).minusDays(1);

        return date.isEqual(ld) || date.isBefore(ld);
    }
}
