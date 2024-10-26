package ku.cs.RPS.validations.licensePlate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class LicensePlateConstraintValidator implements ConstraintValidator<ValidLicensePlate, String> {
    @Override
    public void initialize(ValidLicensePlate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String licensePlate, ConstraintValidatorContext context) {
        if (Pattern.matches("^[ก-ฮ]{2}-\\d{4} [ก-ฮ]{1,}$", licensePlate)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate("ทะเบียนรถไม่สามารถมีตัวอักษรพิเศษได้")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
