package de.ole101.translator.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static java.lang.Integer.parseInt;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class ExceptionController implements ErrorController {

    @GetMapping("/error")
    public Map<String, Serializable> handleError(HttpServletRequest request) {
        int statusCode = Optional.of(parseInt(request.getAttribute(ERROR_STATUS_CODE).toString()))
                .orElse(INTERNAL_SERVER_ERROR.value());
        HttpStatus httpStatus = ofNullable(HttpStatus.resolve(statusCode))
                .orElse(INTERNAL_SERVER_ERROR);

        return Map.of(
                "httpStatus", httpStatus.getReasonPhrase(),
                "httpStatusCode", httpStatus.value(),
                "info", "An error occurred - " + httpStatus.getReasonPhrase()
        );
    }
}
