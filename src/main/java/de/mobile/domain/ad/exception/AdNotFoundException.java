package de.mobile.domain.ad.exception;

public class AdNotFoundException extends RuntimeException {

    public AdNotFoundException(String message) {
        super(message);
    }
}
