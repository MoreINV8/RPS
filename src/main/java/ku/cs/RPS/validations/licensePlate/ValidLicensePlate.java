package ku.cs.RPS.validations.licensePlate;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = LicensePlateConstraintValidator.class)
@Target( {TYPE, FIELD, ANNOTATION_TYPE} )
@Retention(RUNTIME)
public @interface ValidLicensePlate {

    String message() default "Invalid License Plate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
