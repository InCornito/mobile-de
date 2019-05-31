package de.mobile.controller.error;

import de.mobile.controller.validator.AlphaNumericName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public enum ValidationErrorCodes {

    NOT_BLANK(NotBlank.class.getSimpleName(), CommonErrorCodes.ANNOTATION_NOT_BLANK),
    NOT_NULL(NotNull.class.getSimpleName(), CommonErrorCodes.ANNOTATION_NOT_NULL),
    ALPHA_NUMERIC(AlphaNumericName.class.getSimpleName(), CommonErrorCodes.ANNOTATION_ALPHA_NUMERIC_NAME);

    private final String annotationName;
    private final MainErrorCode mainErrorCode;

    ValidationErrorCodes(final String annotationName, final MainErrorCode mainErrorCode) {
        this.annotationName = annotationName;
        this.mainErrorCode = mainErrorCode;
    }

    public static MainErrorCode findErrorCodeByAnnotationName(final String name) {
        return Arrays.stream(values())
                .filter(validationAnnotationErrorCode -> validationAnnotationErrorCode.annotationName.equals(name))
                .map(validationAnnotationErrorCode -> validationAnnotationErrorCode.mainErrorCode)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can't find annotation by name = '" + name + "'."));
    }
}
