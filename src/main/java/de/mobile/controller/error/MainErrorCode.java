package de.mobile.controller.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MainErrorCode {

    private final int code;
    private final String message;
}
