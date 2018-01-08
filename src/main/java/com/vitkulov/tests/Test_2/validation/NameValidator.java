package com.vitkulov.tests.Test_2.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public void initialize(Name name) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext constraintValidatorContext) {
        return nameField != null && nameField.matches("^[a-zA-Zа-яА-Я]+$") && (nameField.length() > 3) && (nameField.length() < 30);
    }
}
