package de.ole101.translator.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final Class<?> clazz;
    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(Class<?> clazz, String message, HttpStatus httpStatus) {
        super(message);
        this.clazz = clazz;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
