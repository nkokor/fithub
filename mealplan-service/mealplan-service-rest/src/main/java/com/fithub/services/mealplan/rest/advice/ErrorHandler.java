package com.fithub.services.mealplan.rest.advice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fithub.services.mealplan.api.exception.ApiException;
import com.fithub.services.mealplan.api.response.ApiError;
import com.fithub.services.mealplan.api.response.ApiErrorResponse;
import com.fithub.services.mealplan.api.response.ApiErrorType;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleModelConstraintViolationException(ConstraintViolationException exception) {
        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        return new ResponseEntity<>(createApiErrorResponse(violations, ConstraintViolation::getMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        final BindingResult bindingResult = exception.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return new ResponseEntity<>(createApiErrorResponse(fieldErrors, DefaultMessageSourceResolvable::getDefaultMessage),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception) {
        final List<ApiError> errors = new ArrayList<>();
        errors.add(new ApiError(ApiErrorType.BUSINESS_LOGIC, exception.getMessage()));

        return new ResponseEntity<>(new ApiErrorResponse(errors), exception.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        final List<ApiError> errors = new ArrayList<>();
        errors.add(new ApiError(ApiErrorType.INTERNAL_SERVER, "Something went wrong."));

        return new ResponseEntity<>(new ApiErrorResponse(errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <T> ApiErrorResponse createApiErrorResponse(Collection<T> errorCollection, Function<T, String> errorMessageGetter) {
        if (errorCollection == null || errorCollection.isEmpty()) {
            return new ApiErrorResponse(new ArrayList<>());
        }

        return new ApiErrorResponse(errorCollection.stream()
                .map(error -> new ApiError(ApiErrorType.MODEL_VALIDATION, errorMessageGetter.apply(error))).collect(Collectors.toList()));
    }
}