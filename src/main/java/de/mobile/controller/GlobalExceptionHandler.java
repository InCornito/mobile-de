package de.mobile.controller;

import de.mobile.controller.error.ErrorCodes;
import de.mobile.controller.error.MainErrorCode;
import de.mobile.controller.error.ValidationErrorCodes;
import de.mobile.controller.error.model.ApiError;
import de.mobile.domain.ad.exception.AdNotFoundException;
import de.mobile.service.impl.MessageResolver;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public final MessageResolver messageResolver;

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleInternalErrorException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Collections.singletonList(
                                new ApiError(ErrorCodes.GENERAL_ERROR.getCode(),
                                        messageResolver.getMessage(ErrorCodes.GENERAL_ERROR.getMessage(), null),
                                        e.getMessage())));
    }

    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity handleAdNotFound(AdNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        Collections.singletonList(
                                new ApiError(ErrorCodes.AD_NOT_FOUND.getCode(),
                                        messageResolver.getMessage(ErrorCodes.AD_NOT_FOUND.getMessage(), null),
                                        e.getMessage())));
    }

    private ApiError createValidationError(ObjectError objectError) {
        MainErrorCode mainErrorCode =
                ValidationErrorCodes.findErrorCodeByAnnotationName(objectError.getCode());

        return Optional.of(objectError)
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .map(fieldError -> {
                    val defaultMessage = fieldError.getDefaultMessage();
                    return new ApiError(
                            ErrorCodes.of(
                                    mainErrorCode.getCode(),
                                    StringUtils.isEmpty(defaultMessage)
                                            ? messageResolver.getMessage(mainErrorCode.getMessage(), new Object[]{})
                                            : defaultMessage
                            ),
                            fieldError.getField(),
                            fieldError.getRejectedValue(),
                            null
                    );
                })
                .orElse(new ApiError(
                        mainErrorCode.getCode(),
                        mainErrorCode.getMessage(),
                        objectError.getDefaultMessage())
                );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError> errorList = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::createValidationError)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(errorList);
    }
}
