package de.ole101.translator.api.exceptions;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            EntryAlreadyExistsException.class,
            EntryNotFoundException.class
    })
    public ResponseEntity<Object> handleApiException(ApiException exception) {
        return buildResponseEntity(exception, exception.getHttpStatus());
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            MismatchedInputException.class
    })
    public ResponseEntity<Object> handleBadRequestException(Exception exception) {
        return buildResponseEntity(exception, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error("Unhandled exception: ", exception);
        return buildResponseEntity(exception, INTERNAL_SERVER_ERROR);
    }

    @NotNull
    private ResponseEntity<Object> buildResponseEntity(@NotNull Exception exception, @NotNull HttpStatus httpStatus) {
        Map<String, Object> body = Map.of(
                "httpStatus", httpStatus.getReasonPhrase(),
                "httpStatusCode", httpStatus.value(),
                "info", ofNullable(exception.getMessage()).orElse(httpStatus.getReasonPhrase())
        );

        return new ResponseEntity<>(body, new HttpHeaders(), httpStatus);
    }
}
