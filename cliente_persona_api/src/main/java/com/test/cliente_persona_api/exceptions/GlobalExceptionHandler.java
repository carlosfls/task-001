package com.test.cliente_persona_api.exceptions;

import com.test.cliente_persona_api.services.util.DetailErrorUtil;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        return getErrorResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> alreadyExistExceptionHandler(AlreadyExistException ex, WebRequest request) {
        return getErrorResponse(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<?> unprocessableEntityExceptionHandler(UnprocessableEntityException ex, WebRequest request) {
        return getErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        return getErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex, WebRequest request) {
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    private ResponseEntity<Object> getErrorResponse(HttpStatusCode httpStatus, Exception ex, WebRequest webRequest) {
        Set<String> errors = getErrorMessages(ex);
        ServletWebRequest servletWebRequest = ((ServletWebRequest) webRequest);
        ErrorDetails errorDetails = buildErrorDetails(servletWebRequest, httpStatus, errors);

        return new ResponseEntity<>(errorDetails, httpStatus);
    }

    private Set<String> getErrorMessages(Exception ex) {
        if (ex instanceof HandlerMethodValidationException handlerMethodValidationException) {
            return handlerMethodValidationException.getAllValidationResults().stream()
                    .flatMap(result -> result.getResolvableErrors().stream())
                    .map(MessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toSet());
        } else if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            return getMethodNotArgumentValidErrors(methodArgumentNotValidException);
        } else {
            return Set.of(DetailErrorUtil.handleErrorCause(ex));
        }
    }

    private ErrorDetails buildErrorDetails(ServletWebRequest servletWebRequest, HttpStatusCode httpStatus, Set<String> errors) {
        return ErrorDetails.builder()
                .uri(servletWebRequest.getRequest().getRequestURL().toString())
                .method(servletWebRequest.getHttpMethod().name())
                .requestDate(LocalDateTime.now())
                .locale(servletWebRequest.getRequest().getLocale())
                .errorName(httpStatus.toString())
                .errorCode(httpStatus.value())
                .details(errors)
                .build();
    }

    private Set<String> getMethodNotArgumentValidErrors(MethodArgumentNotValidException e) {
        return e.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());
    }
}
