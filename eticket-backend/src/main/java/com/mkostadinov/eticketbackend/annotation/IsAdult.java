package com.mkostadinov.eticketbackend.annotation;


import com.mkostadinov.eticketbackend.annotation.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface IsAdult {
    String message() default "{message.key}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
