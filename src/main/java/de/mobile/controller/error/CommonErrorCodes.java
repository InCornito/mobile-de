package de.mobile.controller.error;

public interface CommonErrorCodes {

    MainErrorCode ANNOTATION_NOT_BLANK = ErrorCodes.of(1, "field.not.blank");
    MainErrorCode ANNOTATION_NOT_NULL = ErrorCodes.of(2, "field.not.blank");
    MainErrorCode ANNOTATION_ALPHA_NUMERIC_NAME =
            ErrorCodes.of(10, "alphanumeric.field");
}
