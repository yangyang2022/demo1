package com.hibernate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase,String> {
    private CaseModel caseModel;
    @Override
    public void initialize(CheckCase checkCase) {
        caseModel = checkCase.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        //constraintValidatorContext.disableDefaultConstraintViolation();
        //constraintValidatorContext.buildConstraintViolationWithTemplate("hello").addConstraintViolation();

        return s == null || (caseModel == CaseModel.LOWER ? s.equals(s.toLowerCase()) : s.equals(s.toUpperCase()));
    }
}
