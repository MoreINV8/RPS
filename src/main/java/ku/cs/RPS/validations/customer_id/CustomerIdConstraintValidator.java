package ku.cs.RPS.validations.customer_id;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CustomerIdConstraintValidator implements ConstraintValidator<ValidCustomerId, String> {
    @Override
    public void initialize(ValidCustomerId constraintAnnotation) {

    }

    @Override
    public boolean isValid(String customerId, ConstraintValidatorContext context) {
        if (Pattern.matches("c[0-9]{9}", customerId)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate("รหัสลูกค้าขึ้นต้นด้วย c ตามด้วยตัวเลขจำนวน 9 ตัวอักษร")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
