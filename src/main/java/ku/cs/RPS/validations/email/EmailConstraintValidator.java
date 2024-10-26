package ku.cs.RPS.validations.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ku.cs.RPS.validations.email.ValidEmail;

import java.util.regex.Pattern;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (Pattern.matches(".+@.+[.].+", email)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate("รูปแบบของอีเมลไม่ถูกต้อง")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
