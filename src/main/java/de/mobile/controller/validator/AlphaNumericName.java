package de.mobile.controller.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AlphaNumericName.Validator.class})
public @interface AlphaNumericName {

    String message() default "name must contain only alphanumeric characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Slf4j
    @RequiredArgsConstructor
    @Component
    class Validator implements ConstraintValidator<AlphaNumericName, String> {

        private static final String REGEX = "\\p{Alnum}+";
        private static final Pattern  pattern = Pattern.compile(REGEX);

        @Override
        public void initialize(final AlphaNumericName constraintAnnotation) {
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext context) {
            return !value.isEmpty() && pattern.matcher(value).matches();
        }

    }
}
