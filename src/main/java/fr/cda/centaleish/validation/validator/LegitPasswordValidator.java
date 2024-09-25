package fr.cda.centaleish.validation.validator;

import fr.cda.centaleish.validation.constraint.LegitPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LegitPasswordValidator implements ConstraintValidator<LegitPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (!password.contains("@")) {
//            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Le mot de passe doit contenir un @");
            return false;
        }
        if (password.length() < 8) {
//            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Le mot de passe doit faire au moins 8 caractères");
            return false;
        }
        return true;
    }

}
