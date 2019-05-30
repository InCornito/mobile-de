package de.mobile.controller.error.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.mobile.controller.error.MainErrorCode;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ApiError {

    public ApiError(final int code, final String message, final String debugMessage) {
        this.timestamp = Instant.now();
        this.code = code;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ApiError(final MainErrorCode mainErrorCode,
                    final String field,
                    final Object rejectedValue,
                    final String debugMessage) {
        this.timestamp = Instant.now();
        this.code = mainErrorCode.getCode();
        this.message = mainErrorCode.getMessage();
        this.debugMessage = debugMessage;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    private Instant timestamp;

    private int code;
    private String message;
    private String debugMessage;

    private String field;
    private Object rejectedValue;
}
