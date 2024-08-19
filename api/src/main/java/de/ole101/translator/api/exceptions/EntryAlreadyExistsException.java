package de.ole101.translator.api.exceptions;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;

public class EntryAlreadyExistsException extends ApiException {

    public EntryAlreadyExistsException(Class<?> clazz, Class<?> repository, String entry) {
        super(clazz, format("Entry already exists in %s with qualifier %s", repository, entry), CONFLICT);
    }
}
