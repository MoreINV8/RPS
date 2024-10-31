package ku.cs.RPS.validations.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (Pattern.compile("^[a-zA-Z0-9]{8,}$").matcher(password).find() &&
                password.length() >= 8) {

            return true;
        }

        context.buildConstraintViolationWithTemplate("รหัสผ่านใหม่ต้องไม่มีอักขระพิเศษและความยาวไม่น้อยกว่า 8 ตัว")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
