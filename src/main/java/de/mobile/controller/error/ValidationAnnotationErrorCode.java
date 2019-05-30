package de.mobile.controller.error;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Arrays;

public enum ValidationAnnotationErrorCode {

    NOT_BLANK(NotBlank.class.getSimpleName(), CommonErrorCodes.ANNOTATION_NOT_BLANK);

    private final String annotationName;
    private final MainErrorCode mainErrorCode;

    ValidationAnnotationErrorCode(final String annotationName, final MainErrorCode mainErrorCode) {
        this.annotationName = annotationName;
        this.mainErrorCode = mainErrorCode;
    }

    public static MainErrorCode findErrorCodeByAnnotationCode(final String code) {
        return Arrays.stream(values())
                .filter(validationAnnotationErrorCode -> validationAnnotationErrorCode.annotationName.equals(code))
                .map(validationAnnotationErrorCode -> validationAnnotationErrorCode.mainErrorCode)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can't find annotation by code = '" + code + "'."));
    }
}
