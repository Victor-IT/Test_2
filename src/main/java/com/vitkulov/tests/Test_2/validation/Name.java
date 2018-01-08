package com.vitkulov.tests.Test_2.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String message() default "Имя должно содержать от 3 до 30 букв";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
