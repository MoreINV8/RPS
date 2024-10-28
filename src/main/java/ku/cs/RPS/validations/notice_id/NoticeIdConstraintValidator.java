package ku.cs.RPS.validations.notice_id;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class NoticeIdConstraintValidator implements ConstraintValidator<ValidNoticeId, String> {
    @Override
    public void initialize(ValidNoticeId constraintAnnotation) {

    }

    @Override
    public boolean isValid(String noticeId, ConstraintValidatorContext context) {
        if (Pattern.matches("n[0-9]{9}", noticeId)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate("รหัสใบแจ้งงานคนขับขึ้นต้นด้วย n ตามด้วยตัวเลขจำนวน 9 ตัวอักษร")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
