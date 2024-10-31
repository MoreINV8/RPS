package ku.cs.RPS.validations.licensePlate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class LicensePlateConstraintValidator implements ConstraintValidator<ValidLicensePlate, String> {

    @Override
    public boolean isValid(String licensePlate, ConstraintValidatorContext context) {
        if (Pattern.matches("^[ก-ฮ]{2}-\\d{4} .*$", licensePlate)) {
            return true;
        }

        // Set error message when pattern does not match
        context.buildConstraintViolationWithTemplate("รูปแบบทะเบียนรถไม่ถูกต้อง")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
