package de.mobile.controller.error;

public interface ErrorCodes {

    MainErrorCode GENERAL_ERROR = of(1000, "error.general");
    MainErrorCode AD_NOT_FOUND = of(1001, "ad.not.found");

    static MainErrorCode of(final int code, final String message) {
        return new MainErrorCode(code, message);
    }
}
