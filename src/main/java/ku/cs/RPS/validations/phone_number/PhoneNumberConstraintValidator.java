package ku.cs.RPS.validations.phone_number;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ku.cs.RPS.validations.phone_number.ValidPhoneNumber;

import java.util.regex.Pattern;

public class PhoneNumberConstraintValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (Pattern.matches("[0-9]{10}", phoneNumber)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate("เบอร์มือถือต้องเป็นตัวเลขและมีขนาดเท่ากับ 10 ตัวอักษร")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
